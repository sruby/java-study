import os
import pandas as pd
from torch.utils.data import Dataset
from torchvision.io import read_image

"""A custom Dataset class must implement three functions: __init__, __len__, and __getitem__. Take a look at this implementation; 
the FashionMNIST images are stored in a directory img_dir, and their labels are stored separately in a CSV file annotations_file."""
class CustomImageDataset(Dataset):
    """
    Custom dataset that loads images from a directory and applies transformations to them
    The __init__ function is run once when instantiating the Dataset object. We initialize the directory containing the images, the annotations file,
     and both transforms (covered in more detail in the next section).
    """

    def __init__(self, annotations_file, img_dir, transform=None, target_transform=None):
        self.img_labels = pd.read_csv(annotations_file)
        self.img_dir = img_dir
        self.transform = transform
        self.target_transform = target_transform

    """The __len__ function returns the number of samples in our dataset."""
    def __len__(self):
        return len(self.img_labels)

    """The __getitem__ function loads and returns a sample from the dataset at the given index idx"""
    def __getitem__(self, idx):
        img_path = os.path.join(self.img_dir, self.img_labels.iloc[idx, 0])
        image = read_image(img_path)
        label = self.img_labels.iloc[idx, 1]
        if self.transform:
            image = self.transform(image)
        if self.target_transform:
            label = self.target_transform(label)
        return image, label