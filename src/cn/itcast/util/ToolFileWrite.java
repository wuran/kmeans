package cn.itcast.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/*
 * 此工具类向文件中写入内容
 * */
public class ToolFileWrite {
	/**

	* 用FileWrite向文件写入内容

	* 

	* @param _destFile

	* @throws IOException

	*/

	public static void writeByFileWrite(String _sDestFile, String _sContent)throws IOException {

			FileWriter fw = null;

				try {

						fw = new FileWriter(_sDestFile);

						fw.write(_sContent);

				} catch (Exception ex) {

					ex.printStackTrace();

				} finally {

					if (fw != null) {

						fw.close();

						fw = null;

					}

				}

	}

	 

	/**

	* 用FileOutputStream向文件写入内容

	* 

	* @param _destFile

	* @throws IOException

	*/

	public static void writeByFileOutputStream(String _sDestFile,String _sContent) throws IOException {

			FileOutputStream fos = null;

			try {

				fos = new FileOutputStream(_sDestFile);

				fos.write(_sContent.getBytes());

			} catch (Exception ex) {

				ex.printStackTrace();

			} finally {

				if (fos != null) {

					fos.close();

					fos = null;

				}

			}

	}

	 /**
     * 追加文件：使用FileOutputStream，在构造FileOutputStream时，把第二个参数设为true
     *
     * @param fileName
     * @param content
     */  
		public static void method1(String file, String conent) {  
			BufferedWriter out = null;  
			try {  
				out = new BufferedWriter(new OutputStreamWriter(  
                new FileOutputStream(file, true)));  
				out.write(conent);  
			} catch (Exception e) {  
				e.printStackTrace();  
			} finally {  
				try {  
					out.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
  }   

	/**

	* 用OutputStreamWrite向文件写入内容

	* 

	* @param _destFile

	* @throws IOException

	*/

	public static void writeByOutputStreamWrite(String _sDestFile,String _sContent) throws IOException {

		OutputStreamWriter os = null;

		FileOutputStream fos = null;

		try {

			fos = new FileOutputStream(_sDestFile);

			os = new OutputStreamWriter(fos, "UTF-8");

			os.write(_sContent);

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			if (os != null) {

				os.close();

				os = null;

			}

			if (fos != null) {

				fos.close();

				fos = null;

	}
			}
		}

	 

}


