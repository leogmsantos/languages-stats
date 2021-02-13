package br.com.leogmsantos.languagesstats.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import br.com.leogmsantos.languagesstats.R;

public class LoadingDialog extends Dialog {

    public static LoadingDialog dialog;

    public LoadingDialog(Context context)  {
        super(context, R.style.LanguageStats_Dialog_Loading);
        init();
    }

    private void init(){
        this.setContentView(R.layout.custom_dialog);
        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);
    }

    public static void loading(Context context) {
        try {
            dialog = new LoadingDialog(context);
            dialog.show();
        } catch (Exception e) {
            Log.e(LoadingDialog.class.getSimpleName(), "Loading error: " + e.getMessage());
        }
    }

    public static void dismissLoading() {
        try {
            if (dialog != null) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            Log.e(LoadingDialog.class.getSimpleName(), "Loading dismiss error: " + e.getMessage());
        } finally {
            dialog = null;
        }
    }

}

