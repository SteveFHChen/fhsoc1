package com.web.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URLEncoder;

public class FhClient{
	/* ʵ�飺ʹ��Socket����Http���󣬲����շ��ص���Ӧ
	 * ģ�����������Get��Post��Http����
	 */
	public static void main(String args[]){
		try {
			Socket socket = new Socket("127.0.0.1", 8080);
			//Socket socket = new Socket("10.2.40.83", 8889);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"));
				//ָ������ֽ����еı��뷽ʽΪutf-8����Ϊ�Է�����ʱ����utf-8��ʽ����룬�����ն�ͳһ��utf-8�����߸�ʽһ������Ϳ������շ����ĵ���Ϣ��
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
				//ָ�������ֽ����еı��뷽ʽΪutf-8����Ϊ�Է�����ʱ����utf-8��ʽ����룬�����ն�ͳ����utf-8�����߸�ʽһ������Ϳ������ս����ĵ���Ϣ��
			
			//GET����-����ҳ���������(�ɹ�)
			//bw.write("GET /testweb2/aa.html HTTP/1.1\r\n");
//			bw.write("GET /testweb2/servlet1 HTTP/1.1\r\n");
			bw.write("GET /fhspringmvc/person/say/hello HTTP/1.1\r\n");
			bw.write("Host: localhost:8080\r\n");
			bw.write("Connection: keep-alive\r\n");
			bw.write("Cookie: JSESSIONID=9B2C97369D57A3991ED1B52F46594387\r\n");
			bw.write("\r\n");
			bw.flush();
			
			//POST����-������ǰ�ýӿڷ�������(�ɹ�)
//			bw.write("POST /fsystem/FM0009.do HTTP/1.1\r\n");
//			bw.write("Accept: text/plain, application/json, application/*+json, */*\r\n");
//			bw.write("Content-Type: application/json;charset=UTF-8\r\n");
//			//bw.write("Accept-Charset: big5, big5-hkscs, euc-jp, euc-kr, gb18030, gb2312, gbk, ibm-thai, ibm00858, ibm01140, ibm01141, ibm01142, ibm01143, ibm01144, ibm01145, ibm01146, ibm01147, ibm01148, ibm01149, ibm037, ibm1026, ibm1047, ibm273, ibm277, ibm278, ibm280, ibm284, ibm285, ibm297, ibm420, ibm424, ibm437, ibm500, ibm775, ibm850, ibm852, ibm855, ibm857, ibm860, ibm861, ibm862, ibm863, ibm864, ibm865, ibm866, ibm868, ibm869, ibm870, ibm871, ibm918, iso-2022-cn, iso-2022-jp, iso-2022-jp-2, iso-2022-kr, iso-8859-1, iso-8859-13, iso-8859-15, iso-8859-2, iso-8859-3, iso-8859-4, iso-8859-5, iso-8859-6, iso-8859-7, iso-8859-8, iso-8859-9, jis_x0201, jis_x0212-1990, koi8-r, koi8-u, shift_jis, tis-620, us-ascii, utf-16, utf-16be, utf-16le, utf-32, utf-32be, utf-32le, utf-8, windows-1250, windows-1251, windows-1252, windows-1253, windows-1254, windows-1255, windows-1256, windows-1257, windows-1258, windows-31j, x-big5-hkscs-2001, x-big5-solaris, x-euc-jp-linux, x-euc-tw, x-eucjp-open, x-ibm1006, x-ibm1025, x-ibm1046, x-ibm1097, x-ibm1098, x-ibm1112, x-ibm1122, x-ibm1123, x-ibm1124, x-ibm1364, x-ibm1381, x-ibm1383, x-ibm33722, x-ibm737, x-ibm833, x-ibm834, x-ibm856, x-ibm874, x-ibm875, x-ibm921, x-ibm922, x-ibm930, x-ibm933, x-ibm935, x-ibm937, x-ibm939, x-ibm942, x-ibm942c, x-ibm943, x-ibm943c, x-ibm948, x-ibm949, x-ibm949c, x-ibm950, x-ibm964, x-ibm970, x-iscii91, x-iso-2022-cn-cns, x-iso-2022-cn-gb, x-iso-8859-11, x-jis0208, x-jisautodetect, x-johab, x-macarabic, x-maccentraleurope, x-maccroatian, x-maccyrillic, x-macdingbat, x-macgreek, x-machebrew, x-maciceland, x-macroman, x-macromania, x-macsymbol, x-macthai, x-macturkish, x-macukraine, x-ms932_0213, x-ms950-hkscs, x-ms950-hkscs-xp, x-mswin-936, x-pck, x-sjis_0213, x-utf-16le-bom, x-utf-32be-bom, x-utf-32le-bom, x-windows-50220, x-windows-50221, x-windows-874, x-windows-949, x-windows-950, x-windows-iso2022jp\r\n");
//				//������һ�п���ʡ��
//			//bw.write("User-Agent: Java/1.6.0_45\r\n");
//				//������һ�п���ʡ��
//			bw.write("Host: 10.2.40.83:8889\r\n");
//			bw.write("Connection: keep-alive\r\n");
//			//bw.write("Content-Length: 231\r\n");
//			bw.write("Content-Length: 229\r\n");
//			bw.write("\r\n");
//			//���Է��Ͳ������ĵ�����
//			//bw.write("{\"head\":{\"channel\":\"102\",\"sChannel\":\"102\",\"type\":\"0\",\"format\":\"JSON\",\"version\":\"100\",\"flowId\":\"102201406171033181430859554\",\"tranCode\":\"FM0009\",\"frontDate\":\"20140617\",\"frontTime\":\"103318\",\"appCode\":\"\"},\"body\":{\"custName\":\"abc124\"}}");
//			//���Է��Ͱ����ĵ�����ʹ��utf-8���룩��
//			String s1 = "{\"head\":{\"channel\":\"102\",\"sChannel\":\"102\",\"type\":\"0\",\"format\":\"JSON\",\"version\":\"100\",\"flowId\":\"102201406171033181430859554\",\"tranCode\":\"FM0009\",\"frontDate\":\"20140617\",\"frontTime\":\"103318\",\"appCode\":\"\"},\"body\":{\"custName\":\"����\"}}";
//			/*System.out.println(s1);
//			String s2 = new String(s1.getBytes(), "utf-8");//ע�⣺�������ת��û���ã��Է���utf-8�յ���������Ȼ�����롣
//			System.out.println(s2.length());*/
//			bw.write(s1);
//				//ע�⣺��һ�������\r\n����Ϊ����Ϣ���ǰ�Content-Lengthָ���ĳ��������ġ�
//			bw.flush();
//			/* ���⣺���ָ����utf-8���б��룬�����ͳ�ȥ��
//			 * ��������������ָ��utf-8��ʽ�����ǽ��ַ���ת��utf-8��ʽ���ַ�
//			 */
//			
//			//95508ϵͳ�û�У��ӿڲ���
//			bw.write("POST /fsystem/FM0029.do HTTP/1.1\r\n");
//			bw.write("Accept: text/plain, application/json, application/*+json, */*\r\n");
//			bw.write("Content-Type: application/json;charset=UTF-8\r\n");
//			bw.write("Accept-Charset: big5, big5-hkscs, euc-jp, euc-kr, gb18030, gb2312, gbk, ibm-thai, ibm00858, ibm01140, ibm01141, ibm01142, ibm01143, ibm01144, ibm01145, ibm01146, ibm01147, ibm01148, ibm01149, ibm037, ibm1026, ibm1047, ibm273, ibm277, ibm278, ibm280, ibm284, ibm285, ibm297, ibm420, ibm424, ibm437, ibm500, ibm775, ibm850, ibm852, ibm855, ibm857, ibm860, ibm861, ibm862, ibm863, ibm864, ibm865, ibm866, ibm868, ibm869, ibm870, ibm871, ibm918, iso-2022-cn, iso-2022-jp, iso-2022-jp-2, iso-2022-kr, iso-8859-1, iso-8859-13, iso-8859-15, iso-8859-2, iso-8859-3, iso-8859-4, iso-8859-5, iso-8859-6, iso-8859-7, iso-8859-8, iso-8859-9, jis_x0201, jis_x0212-1990, koi8-r, koi8-u, shift_jis, tis-620, us-ascii, utf-16, utf-16be, utf-16le, utf-32, utf-32be, utf-32le, utf-8, windows-1250, windows-1251, windows-1252, windows-1253, windows-1254, windows-1255, windows-1256, windows-1257, windows-1258, windows-31j, x-big5-hkscs-2001, x-big5-solaris, x-euc-jp-linux, x-euc-tw, x-eucjp-open, x-ibm1006, x-ibm1025, x-ibm1046, x-ibm1097, x-ibm1098, x-ibm1112, x-ibm1122, x-ibm1123, x-ibm1124, x-ibm1364, x-ibm1381, x-ibm1383, x-ibm33722, x-ibm737, x-ibm833, x-ibm834, x-ibm856, x-ibm874, x-ibm875, x-ibm921, x-ibm922, x-ibm930, x-ibm933, x-ibm935, x-ibm937, x-ibm939, x-ibm942, x-ibm942c, x-ibm943, x-ibm943c, x-ibm948, x-ibm949, x-ibm949c, x-ibm950, x-ibm964, x-ibm970, x-iscii91, x-iso-2022-cn-cns, x-iso-2022-cn-gb, x-iso-8859-11, x-jis0208, x-jisautodetect, x-johab, x-macarabic, x-maccentraleurope, x-maccroatian, x-maccyrillic, x-macdingbat, x-macgreek, x-machebrew, x-maciceland, x-macroman, x-macromania, x-macsymbol, x-macthai, x-macturkish, x-macukraine, x-ms932_0213, x-ms950-hkscs, x-ms950-hkscs-xp, x-mswin-936, x-pck, x-sjis_0213, x-utf-16le-bom, x-utf-32be-bom, x-utf-32le-bom, x-windows-50220, x-windows-50221, x-windows-874, x-windows-949, x-windows-950, x-windows-iso2022jp\r\n");
//			bw.write("User-Agent: Java/1.6.0_45\r\n");
//			bw.write("Host: 10.2.40.83:8889\r\n");
//			bw.write("Connection: keep-alive\r\n");
//			bw.write("Content-Length: 770\r\n");
//			bw.write("\r\n");
//			bw.write("{\"head\":{\"channel\":\"102\",\"sChannel\":\"102\",\"type\":\"0\",\"format\":\"JSON\",\"version\":\"100\",\"flowId\":\"102201406171836431831878349\",\"tranCode\":\"FM0029\",\"frontDate\":\"20140617\",\"frontTime\":\"183643\",\"appCode\":\"\"},\"body\":{\"srcAppId\":\"IFSM\",\"id\":\"9550801\",\"password\":\"1B163F18FF13B6091C87F75512D8166C90C5C8CC1B181FC55E6D7865883B90FAB99A7015748295A6FD1E455773C8F7D4779262530AB427CB57F18EAB8AEF7368225C4D2A1E593B2F2B23A9CB01A35080C0815C4A61E944729A3AD6F3B45B875AEC148C393263116F1985ECA745C1CA10CA59E36C45126EB0E58273D5EE9FDA31EF1B782655CFBE9309699506D6D4E9B89583585D0CFFCA49778BD81AF39FCC7DB9552FFD977B8EA60E674D0729E20E028EC0905CFFB48E8F1E9847F004A519E1B33CDDDD64AC2F5B0EF50AC2DEDAE0C1EE635DBC810735CDE4F1BA4889019C331C49ADAE303DAE0C76BE1E55F7C99CBB6E2CAC299624B6EAD7D641FFE2929A85\"}}");
//			bw.flush();
			
			//���ַ����
			int c1;
			char c2;
			while((c1 = br.read())!=-1){
				c2 = (char) c1;
				System.out.print(c2);
			}
			
			//���ַ��ж���
			/*(String line;
			while((line = br.readLine()) != null){
				System.out.println(line);
			}*/
			
			//�Կɽ�����ʽ���벢������Ӧ
			
			
			bw.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}