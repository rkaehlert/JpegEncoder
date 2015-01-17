package main.file.stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import main.exception.common.ExceptionInvalidParameter;

/**
 * The BitWriter buffers bit that will be written byte-by-byte to an output
 * stream.
 *
 *
 *
 */
public class SimpleBitWriter implements BitStream
{
        private FileOutputStream stream;

        private int buffer;

        private byte bufferLength = 0;

        public SimpleBitWriter(File file) throws FileNotFoundException
        {
                this.stream = new FileOutputStream(file);
        }

        public void write(final int val)
        {

                if (val < 0 || val > 255) {
                	throw new ExceptionInvalidParameter("bit value out of range: " + val);
                }

                try {
					this.stream.write(val);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }

        public void writeBit(final int bit)
        {

                if (bit != 0 && bit != 1) {
                       throw new ExceptionInvalidParameter("bit value out of range: " + bit);
                }

                this.buffer |= bit << (7 - this.bufferLength);
                this.bufferLength++;

                if (bufferLength == 8) {

                        write(buffer);

                        this.bufferLength = 0;
                        this.buffer = 0;
                }
        }

        public void writeValue(final int length, final int value)
        {
                if (length > 31) {
                	throw new ExceptionInvalidParameter("more than maximum length: " + value);
                }

                for (int i = length - 1; i >= 0; i--) {
                        writeBit((value >> i) & 1);
                }
        }

        public void write(final byte[] bText)
        {

                writeZeroFillBits();

                int l = bText.length;
                for (int i = 0; i < l; i++) {
                        write(0xFF & bText[i]);
                }
        }
        
        public void write(String bits){
//    		byte[] bval = new BigInteger(bits, 2).toByteArray();
//    		write(bval);
        	for(char bit : bits.toCharArray()){
        		if(bit == '0'){
            		writeBit(0);	
        		}else if(bit == '1'){
        			writeBit(1);
        		}else{
        			throw new ExceptionInvalidParameter("just 1 and zero values allowed. value was: " + bit);
        		}
        	}
        }

        public void writeZeroFillBits(){
                while (this.bufferLength != 0) {
                        writeBit(1);
                }
                this.buffer = 0;
        }
        
        public void writeOneFillBits(){
            while (this.bufferLength != 0) {
                    writeBit(1);
            }
            this.buffer = 0;
        }
        
        public void close(){
        	try {
				this.stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
}
