package sit707_week2;

public class Main 
{
    public static void main( String[] args )
    {
        SeleniumOperations.officeworks_registration_page("https://www.officeworks.com.au/app/identity/create-account");
        SeleniumOperations2.stripe_registeration_page("https://dashboard.stripe.com/register");
    }
}
