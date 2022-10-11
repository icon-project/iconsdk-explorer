package com.dfg.icon.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * QRCODE 관련 Util
 * @author hslee
 *
 */
public class QRcodeUtil {

	private static final Logger logger = LoggerFactory.getLogger(QRcodeUtil.class);

	/** QRCODE 신규 생성
	 *
	 * @param serverUrl   서버 URL
	 * @param qrcodePath  QRCODE가 저장될 위치
	 * @param address     생성할 주소
	 * @return
	 */
	public static String createQrCode(String serverUrl , String qrcodePath ,String address) {

		int qrcodeColor		  = 0xff170410;
		int qrBackgroundColor = 0xFFFFFFFF;

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = null;

		try {
			bitMatrix = qrCodeWriter.encode(address , BarcodeFormat.QR_CODE, 300, 300);
		} catch (WriterException e) {
			logger.error("createQrCode = {}", e);
			return null;
		}

		MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrcodeColor,qrBackgroundColor);
		BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig);

		try {
			ImageIO.write(bufferedImage, "png", new File(qrcodePath+"/"+address+".png"));
		} catch (IOException e) {
			logger.error("createQrCode = {}", e);
			return null;
		}

		return serverUrl + "/qrcode/" + address + ".png";
	}



	/** QRCODE 확인후 없으면 신규 생성
	 * @param serverUrl   서버 URL
	 * @param qrcodePath  QRCODE가 저장될 위치
	 * @param address     생성할 주소
	 * @return true : 성공, 신규 생성  / false : 실패
	 */
	public static String checkQrCode(String serverUrl , String qrcodePath ,String address) {
		File qrcode = new File(qrcodePath+"/"+address+".png");

		if(!qrcode.isFile()) {
			return createQrCode(serverUrl , qrcodePath , address);
		}
		return "OK";
	}
}
