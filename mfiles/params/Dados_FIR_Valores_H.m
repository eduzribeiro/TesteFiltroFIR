%

h = fir1(32,1/3);%%32
freqz(h,1,512)

save('../../data/params/ValoresH.dat','h','-ascii')
