class SearchBaseEngine:
    def __init__(self):
        pass
    def add_conpus(self,file_path):
        with open(file_path,'r') as f:
            text = f.read()
        self.process_text(text)

    def process_text(self,text):
        raise Exception("Not implemented")

