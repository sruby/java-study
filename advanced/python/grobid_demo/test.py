import unittest

from main import *

class TestDemoClass(unittest.TestCase):
    def test_process_pdf_with_grobid(self):
        # Test case 1
        source_pdf_path = './document/CSL-89-1_Epidemic_Algorithms_for_Replicated_Database_Maintenance.pdf'
        output_xml_path = 'output.tei.xml'
        # Call function under test
        process_pdf_with_grobid(source_pdf_path, output_xml_path)
        # Assert the results

        # Test case 2
        source_pdf_path = './document/CSL-89-1_Epidemic_Algorithms_for_Replicated_Database_Maintenance.pdf'
        output_xml_path = 'output.tei.xml'
        # Call function under test
        process_pdf_with_grobid(source_pdf_path, output_xml_path)
        # Assert the results

    def test_convert_xml_to_json(self):
        # Test case 1
        xml_path = 'output.tei.xml'
        json_path = 'output.json'
        # Call function under test
        convert_xml_to_json(xml_path, json_path)
        # Assert the results

    def test_convert_json_to_parquet(self):
        # Test case 1
        json_path = 'output.json'
        parquet_path = 'output.parquet'
        # Call function under test
        convert_json_to_parquet(json_path, parquet_path)
        # Assert the results

