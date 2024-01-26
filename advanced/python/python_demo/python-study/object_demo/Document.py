class Document:
    def __init__(self, title, content,_private_content):
        self.title = title
        self.content = content
        self._private_content = _private_content

    @classmethod
    def create_empty_book(cls,title):
        return cls(title,[], [])

    def get_content_length(self):
        return len(self.content)


book = Document.create_empty_book("test")
print(book.get_content_length())
print(book.title)
