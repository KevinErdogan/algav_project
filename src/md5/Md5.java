package md5;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class Md5 {
	
	private int [] r =  {
			7, 12, 17, 22,  7, 12, 17, 22,  7, 12, 17, 22,  7, 12, 17, 22,
			5,  9, 14, 20,  5,  9, 14, 20,  5,  9, 14, 20,  5,  9, 14, 20,
			4, 11, 16, 23,  4, 11, 16, 23,  4, 11, 16, 23,  4, 11, 16, 23,
			6, 10, 15, 21,  6, 10, 15, 21,  6, 10, 15, 21,  6, 10, 15, 21,	
	}; 
	
	private int h0 = 0x67452301;
	private int h1 = 0xEFCDAB89;
	private int h2 = 0x98BADCFE;
	private int h3 = 0x10325476;

	private final int [] k;
	
	public Md5() {
		k = new int [64];
		for (int i = 0; i < k.length; i++) {
			k[i] =  (int) Math.floor(Math.abs(Math.sin(i + 1)) * Math.pow(2, 32));
		}
	}
	
	
	public String md5(String s) {
		
		//taile padding
		int  sizePadding = s.length()+1;
		
		while(( sizePadding % 512) != 448) {
			 sizePadding++;
		}
		sizePadding +=64;
		
		byte[] padding = Arrays.copyOf(s.getBytes(),  sizePadding/8);
		//System.out.println(padding.length);
		padding[s.length()] = (byte) 128;
		 
		//taille message en 64 bits
		 long messageSize = s.length() * 8;
		 ByteBuffer bb = ByteBuffer.allocate(Long.BYTES).order(ByteOrder.LITTLE_ENDIAN);
		 bb.putLong(messageSize);
		 
		 byte [] bit64_m  = bb.array();
		 int cmp =0;
		 for(int i = padding.length-8;i< padding.length;i++) {
			 padding[i] = bit64_m[cmp];
			 cmp++;
		 }
		
		 ByteBuffer ib;
		 for (int j = 0; j<padding.length;j+=64) {
			 
			 int tmp = j;
			 int[] w = new int [16];
			 for(int i = 0; i<w.length;i++) {
				 byte [] b = {padding[tmp],padding[tmp+1],padding[tmp+2],padding[tmp+3]};
				 ib = ByteBuffer.wrap(b).order(ByteOrder.LITTLE_ENDIAN);
				 w[i] = ib.getInt();
				 tmp +=4;
			 }

		
			int a = h0;
			int b = h1;
			int c = h2;
			int d = h3;
			int f,g,temp;
			        

			for (int i = 0;i<64;i++) {
				if (i<16) {
					f = (b & c) | (~b & d);
					g = i;
				}
				if(i<32) {
					f = (d & b) | (~d & c);
					d = (5*i +1) % 16;
				}
				if(i<47) {
					f = c ^ b ^ d;
					g = (3*i +5) % 16;
				}
				else {
					f = c ^ (b | ~d);
					g = (7*i) % 16;
				}
				
				temp = d;
				d = c;
				c = b;
				b = Integer.rotateLeft(a + f + k[i]+ w[g], r[i])+b;
				a = temp;
					
			}
				
			h0 = h0 + a;
			h1 = h1 + b;
			h2 = h2 + c;
			h3 = h3 + d;
		 }
			
		 	ByteBuffer res = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN);
		 	res.putInt(h0);
		 	res.putInt(h1);
		 	res.putInt(h2);
		 	res.putInt(h3);
		    return toHexString(res.array());
		  }

	
	public static String toHexString(byte[] b)
	  {
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < b.length; i++)
	    {
	      sb.append(String.format("%02X", b[i]));
	    }
	    return sb.toString();
	  }

	}

	

