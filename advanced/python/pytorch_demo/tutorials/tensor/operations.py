import torch

# We move our tensor to the GPU if available
# Attributes of a Tensor
tensorVar = torch.rand(3, 4)

if torch.cuda.is_available():
    tensorVar = tensorVar.to('cuda')

# Standard numpy-like indexing and slicing:
tensorVar = torch.ones(4, 4)
print('First row: ', tensorVar[0])
print('First column: ', tensorVar[:, 0])
print('Last column:', tensorVar[..., -1])
tensorVar[:, 1] = 0
print(tensorVar)

# Joining tensors
# dim=1: This is specifying the dimension along which to concatenate the tensors.
# In PyTorch, a dimension of 0 is the vertical dimension (rows), and a dimension of 1 is the horizontal dimension (columns).
# So, dim=1 means that the tensors are being concatenated side by side (horizontally).
t1 = torch.cat([tensorVar, tensorVar, tensorVar], dim=1)
print(t1)

# Arithmetic operations
# This computes the matrix multiplication between two tensors. y1, y2, y3 will have the same value
print('tensorVar: ', tensorVar)
y1 = tensorVar @ tensorVar.T
print('tensorVar.T: ', tensorVar.T)
print('y1: ', y1)
y2 = tensorVar.matmul(tensorVar.T)
print('y2: ', y2)
y3 = torch.rand_like(tensorVar)
print('y3: ', y3)
torch.matmul(tensorVar, tensorVar.T, out=y3)
print('y3: ', y3)

# This computes the element-wise product. z1, z2, z3 will have the same value
z1 = tensorVar * tensorVar
print('z1: ', z1)
z2 = tensorVar.mul(tensorVar)
print('z2: ', z2)

z3 = torch.rand_like(tensorVar)
print('z3: ', z3)
torch.mul(tensorVar, tensorVar, out=z3)
print('z3: ', z3)

# This computes the matrix multiplication between two tensors
tensor2 = torch.tensor([[1, 2], [3, 4]])
# The transposed tensor https://pytorch.org/docs/stable/generated/torch.transpose.html
# 列转行
tensor2_t = tensor2.T

# Now, let's perform matrix multiplication of tensor and tensor.T:
# The element at the first row and first column of the result is obtained by multiplying elements of the first row of the first matrix by
# the elements of the first column of the second matrix and then summing the products: (1*1 + 2*2) = 1 + 4 = 5.

# The element at the first row and second column of the result is obtained by multiplying elements of the first row of the first matrix by
# the elements of the second column of the second matrix and then summing the products: (1*3 + 2*4) = 3 + 8 = 11.

# The element at the second row and first column of the result is obtained by multiplying elements of the second row of the first matrix by
# the elements of the first column of the second matrix and then summing the products: (3*1 + 4*2) = 3 + 8 = 11.

# The element at the second row and second column of the result is obtained by multiplying elements of the second row of the first matrix by
# the elements of the second column of the second matrix and then summing the products: (3*3 + 4*4) = 9 + 16 = 25.
result = tensor2 @ tensor2_t
print('tensor2: ', tensor2)
print('tensor2_t: ', tensor2_t)
print(result)
