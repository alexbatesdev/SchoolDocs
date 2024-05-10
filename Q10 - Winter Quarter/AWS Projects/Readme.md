# AWS Project Class

## Commands

```
aws lambda create-event-source-mapping --function-name SQS_Consumer_the_Sequel --batch-size 10 --event-source-arn arn:aws:sqs:us-east-2:385155794368:MySecondQueue
```

```
aws lambda list-functions --region us-east-2
```


```
aws sts get-caller-identity \
    --query Account \
    --output text
```