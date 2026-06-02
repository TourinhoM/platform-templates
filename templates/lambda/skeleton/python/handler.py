"""Lambda ${{ values.name }} — ${{ values.description }}"""


def handler(event, context):
    return {
        "statusCode": 200,
        "body": "hello from ${{ values.name }}",
    }
