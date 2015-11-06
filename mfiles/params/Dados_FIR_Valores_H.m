%

h = fir1(32,1/3);%%32
freqz(h,1,512)

save('../../data/ValoresH.dat','h','-ascii')
