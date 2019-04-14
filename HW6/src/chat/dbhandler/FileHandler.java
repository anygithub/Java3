package chat.dbhandler;

import java.io.*;

public class FileHandler {
	
	
	public void checkFileExists(File file) {
	    try {
	        if(!file.exists()){
				file.createNewFile();
	        }
	    } catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public void writeToFile(File file,StringBuilder message) throws IOException {
		checkFileExists(file);
		FileWriter fw = new FileWriter(file,true);
		
		try {
			fw.append(message);
			fw.flush();
		} catch (IOException e) { 
			e.printStackTrace();
		} finally {
			fw.close();
		}
	}
	
	public String printLastLinesFromFile( File file, int lines) {
	    java.io.RandomAccessFile fileHandler = null;
	    try {
	        fileHandler = 
	            new java.io.RandomAccessFile( file, "r" );
	        long fileLength = fileHandler.length() - 1;
	        StringBuilder sb = new StringBuilder();
	        int line = 0;

	        for(long filePointer = fileLength; filePointer != -1; filePointer--){
	            fileHandler.seek( filePointer );
	            int readByte = fileHandler.readByte();

	             if( readByte == 0xA ) {
	                if (filePointer < fileLength) {
	                    line = line + 1;
	                }
	            } else if( readByte == 0xD ) {
	                if (filePointer < fileLength-1) {
	                    line = line + 1;
	                }
	            }
	            if (line >= lines) {
	                break;
	            }
	            sb.append( ( char ) readByte );
	        }

	        String lastLine = sb.reverse().toString();
	        return lastLine;
	    } catch( java.io.FileNotFoundException e ) {
	        e.printStackTrace();
	        return null;
	    } catch( java.io.IOException e ) {
	        e.printStackTrace();
	        return null;
	    }
	    finally {
	        if (fileHandler != null )
	            try {
	                fileHandler.close();
	            } catch (IOException e) {
	            }
	    }
	}
	
	public static String readUsingBufferedReader(String fileName) {
        
        try {
        	BufferedReader reader = new BufferedReader( new FileReader (fileName));
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");
        	while( ( line = reader.readLine() ) != null ) {
			    stringBuilder.append( line );
			    stringBuilder.append( ls );
			}
			stringBuilder.deleteCharAt(stringBuilder.length()-1);
	        reader.close();
	        return stringBuilder.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;  
        
    }
}
