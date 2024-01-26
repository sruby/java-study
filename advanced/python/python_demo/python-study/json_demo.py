import json

# A Python dictionary
person_dict = {
    "name": "John",
    "age": 30,
    "city": "New York",
    "hasChildren": False,
    "titles": ["engineer", "programmer"]
}

# Convert dictionary to JSON (serialization, encoding)
person_json = json.dumps(person_dict)
print("JSON representation:", person_json)

# Convert JSON to dictionary (deserialization, decoding)
person_dict_from_json = json.loads(person_json)
print("Decoded back to dictionary:", person_dict_from_json)

# Writing JSON to a file
with open('person.json', 'w') as json_file:
    json.dump(person_dict, json_file)

# Reading JSON from a file
with open('person.json', 'r') as json_file:
    person_from_file = json.load(json_file)
    print("Read from file:", person_from_file)