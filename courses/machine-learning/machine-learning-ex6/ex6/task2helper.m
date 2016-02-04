load('ex6data3.mat');
train_vec = [0.01 0.03 0.1 0.3 1 3 10 30]';
min_error=intmax;
min_c=-1;
min_sigma=-1;
for C=1:length(train_vec)
    for sigma=1:length(train_vec)
        printf('%d\t%d\n',C,sigma);
        model= svmTrain(X, y, train_vec(C), @(x1, x2) gaussianKernel(x1, x2, train_vec(sigma)));
        predictions = svmPredict(model,Xval);
        error=mean(double(predictions ~= yval));
        disp(error);
        if error<min_error
          min_c=train_vec(C);
          min_sigma=train_vec(sigma);
          min_error=error;
        endif
    endfor
endfor
disp(min_c);
disp(min_sigma);
