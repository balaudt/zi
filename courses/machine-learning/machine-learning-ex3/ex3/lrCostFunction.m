function [J, grad] = lrCostFunction(theta, X, y, lambda)
%LRCOSTFUNCTION Compute cost and gradient for logistic regression with
%regularization
%   J = LRCOSTFUNCTION(theta, X, y, lambda) computes the cost of using
%   theta as the parameter for regularized logistic regression and the
%   gradient of the cost w.r.t. to the parameters.

m = length(y); % number of training examples
h=sigmoid(X*theta);
J = sum(-y.*log(h)-(1-y).*log(1-h))/m+lambda*(sum(theta.^2)-theta(1)^2)/(2*m);
theta(1)=0;
grad = (X'*(h-y))/m+lambda*theta/m;

end
