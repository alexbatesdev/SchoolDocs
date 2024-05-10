Commands:
```ps
docker build -t lambda1-1:1 .
docker run -d -p 9000:8080 --name lambda1-1 lambda1-1:1

docker tag lambda1-1:1 385155794368.dkr.ecr.us-east-2.amazonaws.com/exercise1-1:1
docker push 385155794368.dkr.ecr.us-east-2.amazonaws.com/exercise1-1:1

aws ecr get-login-password --region us-east-2 | docker login --username AWS --password-stdin 385155794368.dkr.ecr.us-east-2.amazonaws.com 
docker push 385155794368.dkr.ecr.us-east-2.amazonaws.com/exercise1-1:1
```

Local Test Endpoint:
localhost:9000/2015-03-31/functions/function/invocations

