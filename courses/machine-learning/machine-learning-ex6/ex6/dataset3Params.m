function [C, sigma] = dataset3Params(X, y, Xval, yval)
%EX6PARAMS returns your choice of C and sigma for Part 3 of the exercise
%where you select the optimal (C, sigma) learning parameters to use for SVM
%with RBF kernel
%   [C, sigma] = EX6PARAMS(X, y, Xval, yval) returns your choice of C and
%   sigma. You should complete this function to return the optimal C and
%   sigma based on a cross-validation set.
%

% You need to return the following variables correctly.
C = 1;
sigma = 0.1;

%load('ex6data3.mat');
%train_vec = [0.01 0.03 0.1 0.3 1 3 10 30]';
%min_error=intmax;
%min_c=-1;
%min_sigma=-1;
%for C=1:length(train_vec)
%    for sigma=1:length(train_vec)
%        printf('%d\t%d\n',C,sigma);
%        model= svmTrain(X, y, train_vec(C), @(x1, x2) gaussianKernel(x1, x2, train_vec(sigma)));
%        predictions = svmPredict(model,Xval);
%        error=mean(double(predictions ~= yval));
%        disp(error);
%        if error<min_error
%          min_c=train_vec(C);
%          min_sigma=train_vec(sigma);
%          min_error=error;
%        endif
%    endfor
%endfor
%disp(min_c);
%disp(min_sigma);
%
% ====================== YOUR CODE HERE ======================
% Instructions: Fill in this function to return the optimal C and sigma
%               learning parameters found using the cross validation set.
%               You can use svmPredict to predict the labels on the cross
%               validation set. For example,
%                   predictions = svmPredict(model, Xval);
%               will return the predictions on the cross validation set.
%
%  Note: You can compute the prediction error using
%        mean(double(predictions ~= yval))
%







% =========================================================================

end
