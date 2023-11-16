import torch
from torch.utils.data import DataLoader
import matplotlib.pyplot as plt
from torchvision.transforms import ToTensor
from torchvision.transforms.v2 import Compose, Resize, ToPILImage, ConvertImageDtype, PILToTensor

from tutorials.datasets_and_dataloaders.creating_a_custom_dataset import CustomImageDataset

# Define a transform that first resizes the images and then converts them to tensors
# transform = Compose([
#     Resize((50, 50),antialias=True),  # Resize all images to be 500x500
#     # ToTensor(),
# ])

# https://poe.com/s/E4sprzi2xg849HO1gzFT
# Define a transform that first converts to PIL Image, then to 3-channel RGB, then resizes the images, and finally converts them to tensors
transform = Compose([
    # ToPILImage(),
    # ConvertImageDtype(dtype=torch.float32), # Convert images to float32
    # PILToTensor(),
    # lambda x: x[:3, :, :] if x.shape[0] == 4 else x,  # Ensure there are only 3 channels
    Resize((50, 50),antialias=True),  # Resize all images to be 50x50
    # ToTensor()
])

# Instantiate your dataset
# Replace 'annotations.csv' and 'image_dir' with your actual paths
# You may also want to supply your own transform functions
training_data = CustomImageDataset('./data/custom_data/labels.csv',
                                      './data/custom_data', transform=transform, target_transform=None)
test_data = CustomImageDataset('./data/custom_data/labels.csv',
                                      './data/custom_data', transform=transform, target_transform=None)
# Create a DataLoader
train_dataloader = DataLoader(training_data, batch_size=2, shuffle=True)
test_dataloader = DataLoader(test_data, batch_size=2, shuffle=True)

# Display image and label.
train_features, train_labels = next(iter(train_dataloader))
print(f"Feature batch shape: {train_features.size()}")
print(f"Labels batch shape: {train_labels.size()}")
# img = train_features[0].squeeze()
label = train_labels[0]

# https://poe.com/s/osPr2Pavn9S6B1VxEtvM
img = train_features[0].numpy().transpose((1, 2, 0))  # Rearrange dimensions to (height, width, channels)
plt.imshow(img, cmap="gray")
plt.show()
print(f"Label: {label}")