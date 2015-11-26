grad = zeros(3,1);
h=sigmoid(X*grad);
J = sum(-y.*log(h)-(1-y).*log(1-h))/m
grad = grad-(1/m).*(X'*(h-y))
for i=1:18
  [cost, grad] = costFunction(grad, X, y);
endfor
cost
grad
