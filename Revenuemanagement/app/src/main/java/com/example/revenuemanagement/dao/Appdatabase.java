package com.example.revenuemanagement.dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.revenuemanagement.entity.Expenditure;
import com.example.revenuemanagement.entity.ExpenditureType;
import com.example.revenuemanagement.entity.Revenue;
import com.example.revenuemanagement.entity.RevenueType;

import java.util.HashMap;

@Database( entities = {RevenueType.class, Revenue.class,Expenditure.class, ExpenditureType.class}, version = 1)
public abstract class Appdatabase extends RoomDatabase {
  public abstract RevenueTypeDao revenueTypeDao();
  public abstract RevunueDao revunueDao();
  public abstract ExpenditureDao expenditureDao();
  public abstract ExpenditureTypeDao expenditureTypeDao();
  private static  Appdatabase INSTANCE;
  private static Callback callback = new Callback() {
      @Override
      public void onCreate(@NonNull SupportSQLiteDatabase db) {
          super.onCreate(db);
          new InitClass(INSTANCE).execute();
      }
  };
  public static Appdatabase getDatabase(final Context context){
      if(INSTANCE == null){
          synchronized (Appdatabase.class){
              INSTANCE = Room.databaseBuilder(context,Appdatabase.class,"revenue_db")
                      .fallbackToDestructiveMigration()
                      .addCallback(callback)// Để khởi tạo dữ liệu ban đầu
                      .build();
          }
      }
      return INSTANCE;
  }

  public static class InitClass extends AsyncTask<Void,Void,Void> {
      private RevenueTypeDao revenueTypeDao;
      private RevunueDao revenueDao;
      public InitClass(Appdatabase db) {
          revenueTypeDao = db.revenueTypeDao();
          revenueDao = db.revunueDao();
      }

      @Override
      protected Void doInBackground(Void... voids) {
          HashMap<String, Object>  data = new HashMap<>();
          HashMap<String, Object>  initRevenue  = new HashMap<>();
          initRevenue.put("date",new String[]{"10-02-2020","27-07-2020","09-04-2020"});
          initRevenue.put("revenue",new String[]{"Trợ cấp","Bảo hiểm","Hoa hồng"});
          initRevenue.put("money",new double[]{100,121,210});
          initRevenue.put("description",new String[]{"Phụ cấp ưu đãi hàng tháng","Mua bảo hiểm nhân thọ","Hoa hồng từ các đại lý bán hàng"});
          data.put("revenuetype",new String[]{"Kinh doanh", "Lương","Đầu tư vốn", "Chuyển nhượng vốn", "Chuyển nhượng bất động sản"});
          data.put("money", new double[]{100,232,123,441,212});
          data.put("description", new String[]{"Thu nhập từ hoạt động sản xuất","tiền công và các khoản có tính chất tiền lương","Tiền lãi cho vay","chuyển nhượng chứng khoán","Chuyển nhượng quyền sử dụng nhà"});
          data.put("date",new String[]{"02-02-2020","22-07-2020","20-05-2020","21-05-2020","22-05-2020"});
          for(int i=0; i< data.size(); i++){
              RevenueType revenueType = new RevenueType();
              revenueType.setTitle(((String[])data.get("revenuetype"))[i]);
              revenueType.setDescription(((String[])data.get("description"))[i]);
              revenueType.setMoney(((double[])data.get("money"))[i]);
              revenueType.setDate(((String[])data.get("date"))[i]);
              revenueTypeDao.insert(revenueType);
          }
          for(int i=0; i< initRevenue.size()-1; i++){
              Revenue revenue = new Revenue();
              revenue.setTitle(((String[])initRevenue.get("revenue"))[i]);
              revenue.setDescription(((String[])initRevenue.get("description"))[i]);
              revenue.setMoney(((double[])initRevenue.get("money"))[i]);
              revenue.setDate(((String[])initRevenue.get("date"))[i]);
              revenueDao.insert(revenue);
          }
          return null;
      }
  }

}
