CLS
 
REM no white-space when setting variables
REM Gonna need to chage the lambda name, image name, and container name

REM Set the path to your .env file
SET ENV_FILE_PATH=.public.env

REM Check if .env file exists
IF NOT EXIST "%ENV_FILE_PATH%" (
    ECHO .env file not found
    EXIT /B 1
)

REM Load variables from .env file
FOR /F "usebackq tokens=1,2 delims==" %%i IN ("%ENV_FILE_PATH%") DO (
    IF "%%i"=="LAMBDA_NAME" SET "LAMBDA_NAME=%%j"
    IF "%%i"=="IMAGE_NAME" SET "IMAGE_NAME=%%j"
    IF "%%i"=="ECR_CONTAINER_NAME" SET "ECR_CONTAINER_NAME=%%j"
)

SET VERSION=1.0
SET ACCTNUM=385155794368
SET REGION=us-east-2
 
 
REM stops and nukes all containers
docker compose down
 
docker build -t %IMAGE_NAME%:%VERSION% .
 
REM not necessary, container is already gone via compose down above, keeping for posterity
REM docker stop test1-performance_add-1
REM docker rm test1-performance_add-1
 
 
 
REM nuke if the old images exist
REM if you don't nuke your local docker images, then even if you make code changes in .py file, they don't get picked up, so no new image appears in ECR.
REM this way, the old ECR gets shelved and this new one appears, with the correct tag, old one just sits there untagged in the garbage pile in ECR i think
 
REM docker rmi performance_add:1.0
docker rmi %IMAGE_NAME%:%VERSION%
 
REM docker rmi 215330149056.dkr.ecr.us-east-2.amazonaws.com/auditionme:performance_add
docker rmi %ACCTNUM%.dkr.ecr.%REGION%.amazonaws.com/%ECR_CONTAINER_NAME%:%IMAGE_NAME%
 
 
 
aws ecr get-login-password --region %REGION% | docker login --username AWS --password-stdin %ACCTNUM%.dkr.ecr.%REGION%.amazonaws.com/%ECR_CONTAINER_NAME%
 
docker compose up -d
 
REM docker tag performance_add:1.0 215330149056.dkr.ecr.us-east-2.amazonaws.com/auditionme:performance_add
docker tag %IMAGE_NAME%:%VERSION% %ACCTNUM%.dkr.ecr.%REGION%.amazonaws.com/%ECR_CONTAINER_NAME%:%IMAGE_NAME%
 
REM docker push 215330149056.dkr.ecr.us-east-2.amazonaws.com/auditionme:performance_add
docker push %ACCTNUM%.dkr.ecr.%REGION%.amazonaws.com/%ECR_CONTAINER_NAME%:%IMAGE_NAME%
 
 
REM you have to point the exisiting lambda to the new container image that was just created, the old ones will just stick around
REM aws lambda update-function-code --publish --function-name abc123 --image-uri 215330149056.dkr.ecr.us-east-2.amazonaws.com/auditionme:performance_add
aws lambda update-function-code --publish --function-name %LAMBDA_NAME% --image-uri  %ACCTNUM%.dkr.ecr.%REGION%.amazonaws.com/%ECR_CONTAINER_NAME%:%IMAGE_NAME%