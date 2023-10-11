/*
 * Copyright 2016 Anton Tananaev (anton.tananaev@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alimert.passportreader.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import org.jmrtd.lds.AbstractImageInfo;
import org.jnbis.WsqDecoder;
import com.gemalto.jp2.JP2Decoder;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;


public class ImageUtil {

    public static Image getImage(Context context, AbstractImageInfo imageInfo) {
        Image image = new Image();
        int imageLength = imageInfo.getImageLength();
        DataInputStream dataInputStream = new DataInputStream(imageInfo.getImageInputStream());
        byte[] buffer = new byte[imageLength];
        try {
            dataInputStream.readFully(buffer, 0, imageLength);
            InputStream inputStream = new ByteArrayInputStream(buffer, 0, imageLength);
            Bitmap bitmapImage = ImageUtil.decodeImage(context, imageInfo.getMimeType(), inputStream);
            image.setBitmapImage(bitmapImage);
            String base64Image = Base64.encodeToString(buffer, Base64.DEFAULT);
            image.setBase64Image(base64Image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public static Bitmap scaleImage(Bitmap bitmap) {
        Bitmap bitmapImage = null;
        if (bitmap != null) {
            double ratio = 400.0 / bitmap.getHeight();
            int targetHeight = (int) (bitmap.getHeight() * ratio);
            int targetWidth = (int) (bitmap.getWidth() * ratio);
            bitmapImage = Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, false);
        }

        return bitmapImage;
    }

    public static Bitmap decodeImage(Context context, String mimeType, InputStream inputStream) throws IOException {
        String mimeTypeLower = mimeType.toLowerCase();

        if (mimeTypeLower.equals("image/jp2") || mimeTypeLower.equals("image/jpeg2000")) {
            return new JP2Decoder(inputStream).decode();
        } else if (mimeTypeLower.equals("image/x-wsq")) {
            WsqDecoder wsqDecoder = new WsqDecoder();
            org.jnbis.Bitmap bitmap = wsqDecoder.decode(inputStream);
            byte[] byteData = bitmap.getPixels();
            int[] intData = new int[byteData.length];
            for (int j=0; j < byteData.length; j++) {
                intData[j] = ((int)0xFF000000) |
                        (((int)byteData[j]) & 0xFF << 16) |
                        (((int)byteData[j]) & 0xFF << 8) |
                        (((int)byteData[j]) & 0xFF);
            }
            return Bitmap.createBitmap(
                    intData,
                    0,
                    bitmap.getWidth(),
                    bitmap.getWidth(),
                    bitmap.getHeight(),
                    Bitmap.Config.ARGB_8888
            );
        } else {
            return BitmapFactory.decodeStream(inputStream);
        }
    }

}
