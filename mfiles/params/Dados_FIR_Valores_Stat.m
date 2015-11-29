%
Data  = load('../../data/data_from_cell/random_activity/SalidaNoAtividade.dat');

T  = Data(:,1);
Ax = Data(:,2);
Ay = Data(:,3);
Az = Data(:,4);

meanX=mean(Ax);
stdX=std(Ax);

meanY=mean(Ay);
stdY=std(Ay);

meanZ=mean(Az);
stdZ=std(Az);

D=[ meanX stdX ]

save('../../data/params/ValoresStatX.dat','D','-ascii')


D=[ meanY stdY ]

save('../../data/params/ValoresStatY.dat','D','-ascii')


D=[ meanZ stdZ ]

save('../../data/params/ValoresStatZ.dat','D','-ascii')
