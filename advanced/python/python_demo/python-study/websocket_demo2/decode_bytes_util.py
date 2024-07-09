from google.protobuf.descriptor import FieldDescriptor
import logging

# 配置日志
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)

class MessageWrapper:
    def __init__(self, proto_message):
        self._proto_message = proto_message
        self._extra_attributes = {}

    def __getattr__(self, name):
        if name in self._extra_attributes:
            return self._extra_attributes[name]
        return getattr(self._proto_message, name)

    def __setattr__(self, name, value):
        if name.startswith('_'):
            super().__setattr__(name, value)
        elif hasattr(self._proto_message, name):
            setattr(self._proto_message, name, value)
        else:
            self._extra_attributes[name] = value

def decode_bytes_fields(message, encoding='gbk'):
    wrapper = MessageWrapper(message)
    for field, value in message.ListFields():
        if field.type == FieldDescriptor.TYPE_BYTES:
            try:
                if isinstance(value, bytes):
                    decoded_value = value.decode(encoding)
                    wrapper.__setattr__(f"{field.name}_decoded", decoded_value)
                elif isinstance(value, str):
                    logger.warning(f"Field '{field.name}' is already a string, expected bytes")
                else:
                    logger.warning(f"Unexpected type for field '{field.name}': {type(value)}")
            except UnicodeDecodeError:
                logger.warning(f"Unable to decode field '{field.name}' using {encoding} encoding", exc_info=True)
        elif field.type == FieldDescriptor.TYPE_MESSAGE:
            if field.label == FieldDescriptor.LABEL_REPEATED:
                for item in value:
                    decode_bytes_fields(item, encoding)
            else:
                decode_bytes_fields(value, encoding)
    return wrapper