1. ./mvnw clean package
1. cf cups azure-servicebus -p servicebus-credentials.json
1. cf push -f manifest-south.yml
1. switch target clouds
1. cf push -f manifest-north.yml
1. Applaud Maggie
