echo "Adding some more devices. \n"
curl -H "Content-Type: application/json" -X POST -d '{"serial_number": "111-4566789","machine_code":"ProductionFloor","device_name":"Ignatz"}' http://localhost:8080/devicemanager/add
curl -H "Content-Type: application/json" -X POST -d '{"serial_number": "AC-66879","machine_code":"ShippingDock","device_name":"GuessWho"}' http://localhost:8080/devicemanager/add
curl -H "Content-Type: application/json" http://localhost:8080/devicemanager/fetchbyserialnumber/111-4566789
curl -H "Content-Type: application/json" http://localhost:8080/devicemanager/fetchbyserialnumber/111-4566789
curl -H "Content-Type: application/json" http://localhost:8080/devicemanager/fetchbymachinecode/ProductionFloor
curl -H "Content-Type: applicetion/json" http://localhost:8080/devicemanager/getalldevices