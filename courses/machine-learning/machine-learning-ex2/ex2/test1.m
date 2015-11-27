
theta1=[1 1 2.4;1 1.7 3.2];
theta2=[1 0.3 -1.2];
theta1c=[1 1.7 3.2;1 1 2.4];
theta2c=[1 -1.2 0.3];
for i=1:10
  x=rand(1,2);
  x=[1 x]';
  a2=sigmoid(theta1*x);
  a2=[1;a2];
  a3=sigmoid(theta2*a2)

  a2c=sigmoid(theta1c*x);
  a2c=[1;a2c];
  a3c=sigmoid(theta2c*a2c)

  printf('\n');
endfor
