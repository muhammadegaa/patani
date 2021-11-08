import asyncio
import time
import json
from gpiozero import CPUTemperature

from azure.iot.device.aio import IoTHubDeviceClient

def get_temp():
    temperature_value = (cpu.temperature-32)*(5/9)
    return temperature_value

async def main():
    conn_str="HostName=xxx"
    device_client = IoTHubDeviceClient.create_from_connection_string(conn_str)
    await device_client.connect()

    last_temp = ""

    while True:
        temp = "{0:0.1f}".format(get_temp())
        print("Temperature", temp)

        if temp != last_temp:
            last_temp = temp

            data = {}
            data['temperature'] = temp
            json_body = json.dumps(data)
            print('Sending message: ', json_body)
            await device_client.send_message(json_body)

        time.sleep(1)
    
    await device_client.disconnect()

if __name__ == "__main__":
    asyncio.run(main())