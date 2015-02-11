/*
 * Zed Attack Proxy (ZAP) and its related class files.
 * 
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 * 
 * Copyright The ZAP Development Team
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package org.zaproxy.zap.utils;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

import org.parosproxy.paros.model.Model;


public class DisplayUtils {
	
	private static float scale = -1;
	
	private static Boolean scaleImages = null;
	
	private static boolean isScaleImages() {
		if (scaleImages == null) {
			scaleImages = Model.getSingleton().getOptionsParam().getViewParam().isScaleImages();
		}
		return scaleImages;
	}

	public static float getScale() {
		if (scale == -1) {
			scale = FontUtils.getFont(FontUtils.Size.standard).getSize2D() / FontUtils.systemDefaultFont.getSize2D(); 
		}
		return scale;
	}
	
	public static ImageIcon getScaledIcon(ImageIcon icon) {
		if (! isScaleImages() || icon == null || getScale() == 1) {
			// dont need to scale
			return icon;
		}
		return new ImageIcon(((ImageIcon)icon).getImage().getScaledInstance(
				(int)(icon.getIconWidth() * getScale()), (int)(icon.getIconHeight() * getScale()), Image.SCALE_SMOOTH));
	}
	
	public static Dimension getScaledDimension(int width, int height) {
		if (getScale() == 1) {
			// dont need to scale
			return new Dimension(width, height);
		}
		return new Dimension((int)(width * getScale()), (int)(height * getScale()));
		
	}
	
	public static void scaleIcon(JLabel label) {
		if (isScaleImages() && label != null && label.getIcon() != null && label.getIcon() instanceof ImageIcon) {
			label.setIcon(getScaledIcon((ImageIcon)label.getIcon()));
		}
	}
	
	public static void scaleIcon(JButton button) {
		if (isScaleImages() && button != null && button.getIcon() != null && button.getIcon() instanceof ImageIcon) {
			button.setIcon(getScaledIcon((ImageIcon)button.getIcon()));
		}
	}

	public static void scaleIcon(JToggleButton button) {
		if (isScaleImages() && button != null) {
			if (button.getIcon() != null && button.getIcon() instanceof ImageIcon) {
				button.setIcon(getScaledIcon((ImageIcon)button.getIcon()));
			}
			if (button.getRolloverIcon() != null && button.getRolloverIcon() instanceof ImageIcon) {
				button.setRolloverIcon(getScaledIcon((ImageIcon)button.getRolloverIcon()));
			}
		}
	}
}