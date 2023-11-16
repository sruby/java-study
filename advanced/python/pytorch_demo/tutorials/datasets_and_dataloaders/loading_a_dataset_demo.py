import torch
from torch.utils.data import Dataset
from torchvision import datasets
from torchvision.transforms import ToTensor
import matplotlib.pyplot as plt

# Loading a Dataset
training_data = datasets.FashionMNIST(
    root="data",
    train=True,
    download=True,
    transform=ToTensor()
)

test_data = datasets.FashionMNIST(
    root="data",
    train=False,
    download=True,
    transform=ToTensor()
)

# Iterating and Visualizing the Dataset
# A labels_map dictionary is defined. This maps integer labels to their corresponding class
# names in a dataset of clothing items.
labels_map = {
    0: "T-Shirt",
    1: "Trouser",
    2: "Pullover",
    3: "Dress",
    4: "Coat",
    5: "Sandal",
    6: "Shirt",
    7: "Sneaker",
    8: "Bag",
    9: "Ankle Boot",
}
# A figure is created using Matplotlib's plt.figure function.
# The figsize argument sets the size of the figure.
figure = plt.figure(figsize=(8, 8))
# The number of columns (cols) and rows (rows) for the subplots in the figure are defined.
# In Python, you can assign values to multiple variables in a single line.
# This is called multiple assignment or tuple unpacking.
cols, rows = 3, 3
# A loop runs for a number of times equal to the total number of subplots (cols * rows).
for i in range(1, cols * rows + 1):
    # In each iteration, a random index (sample_idx) is generated within the range of the length of training_data.
    sample_idx = torch.randint(len(training_data), size=(1,)).item()
    print('sample_idx:', sample_idx)
    # The image and label at sample_idx are retrieved from training_data.
    img, label = training_data[sample_idx]
    print('label:', label)
    print('img:', img)
    # A new subplot is added to the figure using figure.add_subplot.
    figure.add_subplot(rows, cols, i)
    # The title of the subplot is set to the class name corresponding to the label using labels_map.
    plt.title(labels_map[label])
    # The axis is turned off with plt.axis("off").
    plt.axis("off")
    # The image is shown in the subplot using plt.imshow. The squeeze() function is called on the image tensor to remove any
    # dimensions of size 1. The cmap argument is set to "gray" to display the image in grayscale.
    plt.imshow(img.squeeze(), cmap="gray")
#After the loop, plt.show() is called to display the figure with all the subplots.
plt.show()

