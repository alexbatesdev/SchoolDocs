# Stuffed Animal Dating App (Stitch)

## Prerequisites

- [Docker](https://www.docker.com/products/docker-desktop)

## Steps to run the app

In the time since I finished this project and now, I have lost the ability to run the app. There are installation conflicts or something of the sort. `pip install -r requirements.txt` should work, but it's giving both me and docker errors. I'm sorry for the inconvenience.

1. Clone the repository
2. Run `docker-compose up` in the root directory
3. Fix the dependency agacent errors

## Env variables

auth-api
```yaml
jwt_secret_key: goobledegook
```
email-service
```yaml
MAIL_PASSWORD: example_password
MAIL_FROM: user@example.com
MAIL_PORT: 587
MAIL_SERVER: smtp.gmail.com
```
profile-api
```yaml
jwt_secret_key: same-gobledegook-as-above #same as auth-api secret key
CLOUD_NAME: CLOUDINARY_NAME
CLOUDINARY_SECRET: CLOUDINARY_SECRET
CLOUDINARY_API_KEY: CLOUDINARY_API_KEY
```

## Architecture

<img src="./Diagrams/ServiceDiagram2.png" alt="Architecture" width="350px" />