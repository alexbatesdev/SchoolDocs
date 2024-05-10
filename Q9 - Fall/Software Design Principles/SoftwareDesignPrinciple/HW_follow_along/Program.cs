namespace HW_follow_along
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Dec_MyWriteFile file = new Dec_MyWriteFile(new Dec_Encrypt(new Dec_Signature(null, " - Siggynature")));

            file.WriteFile("Flibbity jibber jabber");
        }
    }
}