using CorpRest_DataIntegration.Corp;
using CorpRest_DataIntegration.Restaurant_A;
using CorpRest_DataIntegration.Restaurant_B;

namespace CorpRest_DataIntegration
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello, World!");
            Menu_B menuB = new Menu_B();
            Menu_A menuA = new Menu_A();
            CorpMenu menu = new CorpMenu();

            menu.AddItem(menuA);
            menu.AddItem(menuB);


            menu.Print();
        }
    }
}