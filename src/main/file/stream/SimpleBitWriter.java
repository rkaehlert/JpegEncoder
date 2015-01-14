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

        /** Output buffer */
        private FileOutputStream stream;

        /** Buffer to store the bits */
        private int buffer;

        /** Number of stored bits */
        private byte bufferLength = 0;

        /**
         * Constructor Creates a BitWriter with a standard buffer.
         * @throws FileNotFoundException 
         */
        public SimpleBitWriter(File file) throws FileNotFoundException
        {
                this.stream = new FileOutputStream(file);
        }

        /**
         * Writes a byte to the buffer.
         *
         * @param val
         *            an integer representing a full byte
         *
         * @throws EncodingException
         *             if the value is out range
         */
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

        /**
         * Writes a single bit to the buffer.
         *
         * @param bit
         *            0 or 1
         * @throws EncodingException
         *             if the input is neither 0 nor 1.
         */
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

        /**
         * Writes a positive integer to the buffer.
         *
         * @param length
         *            the number of bits to write
         * @param value
         *            an integer value
         *
         * @throws EncodingException
         *             if the length of the input is more than 31 bits.
         */
        public void writeValue(final int length, final int value)
        {
                if (length > 31) {
                	throw new ExceptionInvalidParameter("more than maximum length: " + value);
                }

                for (int i = length - 1; i >= 0; i--) {
                        writeBit((value >> i) & 1);
                }
        }

        /**
         * Writes the byte array to the buffer. The currently used buffer will be
         * filled with zero bits before is is written in front of the byte-array.
         *
         * @param bText
         *            byte array
         *
         * @throws EncodingException
         *             if the writing fails
         */
        public void write(final byte[] bText)
        {

                writeFillBits();

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

        /**
         * The currently used buffer will be filled with zero bits before is is
         * written in the buffer.
         *
         * @throws EncodingException
         *             if the writing fails
         */
        public void writeFillBits()
        {

                while (this.bufferLength != 0) {
                        writeBit(0);
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
