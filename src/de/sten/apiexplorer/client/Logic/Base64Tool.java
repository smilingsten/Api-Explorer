//stripped down version of original by Christian d'Heureuse, Inventec Informatik AG, Zurich, Switzerland;

//Original Copyright and License:
// Copyright 2003-2010 Christian d'Heureuse, Inventec Informatik AG, Zurich, Switzerland
// www.source-code.biz, www.inventec.ch/chdh
//
// This module is multi-licensed and may be used under the terms
// of any of the following licenses:
//
//  EPL, Eclipse Public License, V1.0 or later, http://www.eclipse.org/legal
//  LGPL, GNU Lesser General Public License, V2.1 or later, http://www.gnu.org/licenses/lgpl.html
//  GPL, GNU General Public License, V2 or later, http://www.gnu.org/licenses/gpl.html
//  AL, Apache License, V2.0 or later, http://www.apache.org/licenses
//  BSD, BSD License, http://www.opensource.org/licenses/bsd-license.php

package de.sten.apiexplorer.client.Logic;

public class Base64Tool {

	private static char[]    charmap = new char[64];
	   static {
	      int i=0;
	      for (char c='A'; c<='Z'; c++) charmap[i++] = c;
	      for (char c='a'; c<='z'; c++) charmap[i++] = c;
	      for (char c='0'; c<='9'; c++) charmap[i++] = c;
	      charmap[i++] = '+'; charmap[i++] = '/'; }

	public static String encodeString (String s) {
	   return new String(encode(s.getBytes())); }


	public static char[] encode (byte[] in) {
	   return encode(in, 0, in.length); }

	public static char[] encode (byte[] in, int iOff, int iLen) {
	   int oDataLen = (iLen*4+2)/3;       // output length without padding
	   int oLen = ((iLen+2)/3)*4;         // output length including padding
	   char[] out = new char[oLen];
	   int ip = iOff;
	   int iEnd = iOff + iLen;
	   int op = 0;
	   while (ip < iEnd) {
	      int i0 = in[ip++] & 0xff;
	      int i1 = ip < iEnd ? in[ip++] & 0xff : 0;
	      int i2 = ip < iEnd ? in[ip++] & 0xff : 0;
	      int o0 = i0 >>> 2;
	      int o1 = ((i0 &   3) << 4) | (i1 >>> 4);
	      int o2 = ((i1 & 0xf) << 2) | (i2 >>> 6);
	      int o3 = i2 & 0x3F;
	      out[op++] = charmap[o0];
	      out[op++] = charmap[o1];
	      out[op] = op < oDataLen ? charmap[o2] : '='; op++;
	      out[op] = op < oDataLen ? charmap[o3] : '='; op++; }
	   return out; }
}


