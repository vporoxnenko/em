package com.nsisc.emconnector.core.web.gwt.client.widgets;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.Dialog;

public class AlertDialog {

	public enum ButtonsEnum {
		OK(Dialog.OK), YES_NO(Dialog.YESNO);

		private String value;

		ButtonsEnum(String buttons) {
			this.value = buttons;
		}

		public String toString() {
			return value;
		}

	}

	public static void showAlert(String message, ButtonsEnum buttons) {
		Dialog dialog = new Dialog();
		dialog.setHeading("Alert");
		dialog.setBodyStyleName("pad-text");
		dialog.setScrollMode(Scroll.AUTO);
		dialog.setHideOnButtonClick(true);
		dialog.addText(message);
		dialog.setButtons(buttons.toString());
		dialog.show();
	}
	
	public static void showAlert(String message){
		showAlert(message, ButtonsEnum.OK);
	}
	
	public static void showError(String message, Throwable th){
		showAlert(new StringBuilder(message).append("<br/>").append(th.getMessage()!=null?th.getMessage():th).toString(), ButtonsEnum.OK);
	}

}
