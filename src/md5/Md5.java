package md5;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;


import cle.Cle;

public class Md5 {
	
	private int [] r =  {
			7, 12, 17, 22,  7, 12, 17, 22,  7, 12, 17, 22,  7, 12, 17, 22,
			5,  9, 14, 20,  5,  9, 14, 20,  5,  9, 14, 20,  5,  9, 14, 20,
			4, 11, 16, 23,  4, 11, 16, 23,  4, 11, 16, 23,  4, 11, 16, 23,
			6, 10, 15, 21,  6, 10, 15, 21,  6, 10, 15, 21,  6, 10, 15, 21,	
	}; 
	
	private static final int h0 = 0x67452301;
	private static final int h1 = 0xEFCDAB89;
	private static final int h2 = 0x98BADCFE;
	private static final int h3 = 0x10325476;

	private final int [] k;
	
	public Md5() {
		k = new int [64];
		for (int i = 0; i < k.length; i++) {
			k[i] =  (int) (long) Math.floor(Math.abs(Math.sin(i + 1)) * Math.pow(2, 32));
		}
	}
	
	
	public String md5(String s) {
		
		byte [] message = s.getBytes();
		
		int sizePadding = message.length+1;
		
		while((sizePadding % 512 ) != 448) {
			sizePadding ++;
		}
		
		long lengthInBits = (long)message.length * 8;
		sizePadding += 64;
		
		ByteBuffer bb = ByteBuffer.allocate(sizePadding/8).order(ByteOrder.LITTLE_ENDIAN);
	  	bb.put(message);
	   	bb.put((byte)0x80);
	        bb.putLong(bb.capacity() - 8, lengthInBits);
		bb.rewind();
		
		 int a_mem = h0;
	         int b_mem = h1;
	 	 int c_mem = h2;
		 int d_mem = h3;
		 while(bb.hasRemaining()) {
			
		    IntBuffer w = bb.slice().order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
		    int a = h0;
	            int b = h1;
	 	    int c = h2;
		    int d = h3;
		    int f,g,temp;
		    
			    for (int i = 0;i<64;i++) {
					if(i>=0 && i<=15)  {
						f = (b & c) | (~b & d);
						g = i;
					}
					else if(i>=16 && i<=31) {
						f = (b & d) | (~d & c);
						g = (5*i + 1) % 16;
					}
					else if(i>=32 && i<=47) {
						f = b ^ c ^ d;
						g = (3*i + 5) % 16;
					}
					else {
						f = c ^ (b | ~d);
						g = (7*i) % 16;
					}
					
					temp = d;
					d = c;
					c = b;
					b = Integer.rotateLeft(a + f + k[i]+ w.get(g), r[i])+b;
					a = temp;
			    }
				
			a_mem +=a;
			b_mem +=b;
			c_mem +=c;
			d_mem +=d;
			
			bb.position(bb.position() + 64);
		 }
		
		 
		 ByteBuffer res = ByteBuffer.allocate(16).order(ByteOrder.LITTLE_ENDIAN);
		 res.putInt(a_mem);
		 res.putInt(b_mem);
		 res.putInt(c_mem);
		 res.putInt(d_mem);
		 return toHexString(res.array());
		 
		// return new Cle (h0,h1,h2,h3);
	}


	
	public static String toHexString(byte[] b){
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < b.length; i++)
	    {
	      sb.append(String.format("%02X", b[i]));
	    }
	    return sb.toString();
	}

	
	public static void main(String[] args) {
		Md5 md = new Md5();
		
		System.out.println("0X"+md.md5("a"));
		System.out.println("0X"+md.md5(""));
		System.out.println("0X"+md.md5("abc"));
	
	}
}

