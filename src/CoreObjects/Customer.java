/**
 *  Class : Custumer.java 
 *  This class Contains fields about company
 *  Custumer Basic infos will be transfered at DataBase
 */
package CoreObjects;

public class Customer {  
    // ===== Set Methods =======================================
        public void SetFirstName(String FirstName){
            this.FirstName = FirstName;
        }
        public void SetLastName(String LastName){
            this.LastName = LastName;
        }
        public void SetPhone(String Phone){
            this.Phone = Phone;
        }
        public void SetAddress(String Address){
            this.Address = Address;
        }
        public void SetCity(String City){
            this.City = City;
        }
        public void SetPostalCode(int PostalCode){
            this.PostalCode = PostalCode;
        }
        public void SetCompany(String Company){
            this.Company = Company;
        }
        public void SetMail(String Mail){
            this.Mail = Mail;
        }
    // =========================================================
    
    // ===== Get Methods =======================================
        public String GetFirstName(){
            return FirstName;
        } 
        public String GetLastName(){
            return LastName;
        }
        public String GetPhone(){
            return Phone;
        }
        public String GetAddress(){
            return Address;
        }
        public String GetCity(){
            return City;
        }
        public int GetPostalCode(){
            return PostalCode;
        }
        public String GetCompany(){
            return Company;
        }
        public String GetMail(){
            return Mail;
        }
    // =========================================================
    
    // ==== Decleration ========================================
        private String FirstName;
        private String LastName;
        private String Phone;
        private String Address;
        private String City;
        private int PostalCode;
        private String Company;
        private String Mail;
    // =========================================================
}
