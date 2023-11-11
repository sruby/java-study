import os
import requests
import xmltodict
import json
import pandas as pd
import pyarrow

GROBID_API_URL = 'http://localhost:8070/api/processFulltextDocument'
SOURCE_PDF_PATH = './document/CSL-89-1_Epidemic_Algorithms_for_Replicated_Database_Maintenance.pdf'
OUTPUT_XML_PATH = 'output.tei.xml'
OUTPUT_JSON_PATH = 'output.json'
OUTPUT_PARQUET_PATH = 'output.parquet'

def process_pdf_with_grobid(pdf_path, output_path):
    try:
        with open(pdf_path, 'rb') as f:
            files = {'input': f}
            response = requests.post(GROBID_API_URL, files=files)
            if response.status_code == 200:
                with open(output_path, 'w', encoding='utf-8') as f:
                    f.write(response.text)
            else:
                print(f'Error: {response.status_code}')
    except Exception as e:
        print(f'Error processing pdf with grobid: {e}')

def convert_xml_to_json(xml_path, json_path):
    try:
        with open(xml_path, encoding='utf-8') as in_file:
            xml = in_file.read()
            data_dict = xmltodict.parse(xml)
            json_data = json.dumps(data_dict)
            with open(json_path, 'w', encoding='utf-8') as out_file:
                out_file.write(json_data)
    except Exception as e:
        print(f'Error converting xml to json: {e}')

def convert_json_to_parquet(json_path, parquet_path):
    if not os.path.exists(json_path):
        print(f'Error: {json_path} does not exist')

    print(f'Converting {json_path} to {parquet_path}')
    try:
        df = pd.read_json(json_path)
        df.to_parquet(parquet_path)
    except Exception as e:
        print(f'Error converting json to parquet: {e}')

if __name__ == '__main__':
    process_pdf_with_grobid(SOURCE_PDF_PATH, OUTPUT_XML_PATH)
    convert_xml_to_json(OUTPUT_XML_PATH, OUTPUT_JSON_PATH)
    convert_json_to_parquet(OUTPUT_JSON_PATH, OUTPUT_PARQUET_PATH)