# Patani by Dosirak Tech

![alt text](https://github.com/muhammadegaa/patani/blob/main/sys-arch.png)

Data flow
1.	GIS software will be used to process public satellite image data into csv format to simplify the machine learning model training process
2.	Device Provisioning Service will be used to simplify process of provisioning multiple IoT devices
3.	Events generated from IoT devices are sent to Azure Function Apps for data transformation through Azure IoT Hub as a stream of messages. Azure IoT Hub stores streams of data in partitions for a configurable amount of time.
4.	Data will be stored in Cosmos DB since it is supports NoSQL and historical data will be used for ML model and feature improvement
5.	Baseline data from land suitability atlas, satellite data in csv format, and transformed IoT devices data will be collected in Azure blob storage 
6.	The data that has been collected in the Azure Blob Storage will be used to develop machine learning models using Azure Machine Learning services
7.	Some data may be directly consumed without going through the machine learning model. The backend functionality will be implemented on the mobile apps and will directly call data from Azure Blob Storage via REST API
8.	Machine learning model results will be deployed using Azure ML Web Services which will then be consumed by mobile apps
