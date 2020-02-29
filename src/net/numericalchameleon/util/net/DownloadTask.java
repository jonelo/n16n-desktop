/**
 *
 * NumericalChameleon 3.0.0 - more than an unit converter - a NumericalChameleon
 * Copyright (c) 2001-2020 Dipl.-Inf. (FH) Johann Nepomuk Loefflmann, All Rights
 * Reserved, <http://www.numericalchameleon.net>.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package net.numericalchameleon.util.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.SwingWorker;

public class DownloadTask extends SwingWorker<Boolean, Void> {

    //private ResourceBundle rb;
    private String[] addresses;
    private File localFile;
    private Boolean success;
    private String userAgent;
    private DownloadTaskInterface downloadTaskInterface;

    public DownloadTask(DownloadTaskInterface downloadTaskInterface, String address, File localFile, String userAgent) {
        this.downloadTaskInterface = downloadTaskInterface;
        this.addresses = new String[1];
        this.addresses[0] = address;
        this.localFile = localFile;
        this.userAgent = userAgent;
    }

    public DownloadTask(DownloadTaskInterface downloadTaskInterface, String[] addresses, File localFile, String userAgent) {
        this.downloadTaskInterface = downloadTaskInterface;
        this.addresses = addresses;
        this.localFile = localFile;
        this.userAgent = userAgent;
    }

    public DownloadTask(DownloadTaskInterface downloadTaskInterface, String address, File localFile) {
        this(downloadTaskInterface, address, localFile, null);
    }

    public DownloadTask(DownloadTaskInterface downloadTaskInterface, String[] addresses, File localFile) {
        this(downloadTaskInterface, addresses, localFile, null);
    }

    @Override
    public Boolean doInBackground() {

        downloadTaskInterface.downloadProcessStarted();
        setProgress(0);
        int files = addresses.length;
        int pieceInPercent = 100 / files;

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(localFile));
            int filesDone = 0;

            String actualUserAgent = null;
            if (userAgent != null) {
                if (userAgent.equals("random")) {
                    actualUserAgent = UserAgent.getRandomUserAgent();
                } else {
                    actualUserAgent = userAgent;
                }
            }

            for (String address : addresses) {
                URL url = new URL(address);
                URLConnection connection = url.openConnection();
                // set a user agent if it is desired
                if (actualUserAgent != null) {
                    // System.out.println("Using user-agent: " + actualUserAgent);
                    connection.setRequestProperty("User-Agent", actualUserAgent);
                }

                int contentLength = connection.getContentLength();

                if (contentLength < 0) { // length is not known
                    contentLength = 4096; // schätzen, 4KB
                }

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()));

                String line;
                int sum = 0;
                while ((line = in.readLine()) != null) {

                    out.write(line);
                    out.write("\n");
                    sum = sum + line.length() + 1;

                    int percent = sum * 100 / contentLength;
                    if (percent > 100) {
                        percent = 100;
                    }
                    setProgress(filesDone * pieceInPercent + pieceInPercent * percent / 100);
                }
                filesDone++;
                in.close();
            }
            out.close();
            setProgress(0);
            success = Boolean.TRUE;
        } catch (IOException e) {
            success = Boolean.FALSE;
            System.out.println(e);
        }
        return Boolean.TRUE;
    }

    /*
         * Executed in event dispatching thread
     */
    @Override
    public void done() {
        downloadTaskInterface.downloadProcessStopped();
        if (success) {
            downloadTaskInterface.downloadWasSuccessful();
        } else {
            downloadTaskInterface.downloadWasNotSuccessful();
        }
    }
}
