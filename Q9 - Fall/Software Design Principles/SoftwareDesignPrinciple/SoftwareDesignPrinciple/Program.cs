using Decorators;
using System.Security.Cryptography.X509Certificates;

namespace SoftwareDesignPrinciple
{
    internal class Program
    {
        static void Main(string[] args)
        {
            //Bake a cake
            Console.WriteLine("Hello, World!");

            //ICakeItem cake = new Dec_CakeLayer(new Dec_Frosting(new Dec_Sprinkles(null, Flavor.BirthdayCake, Shape.Stars), Flavor.Chocolate));
            ICakeItem cake = new Dec_CakeLayer(
                new Dec_CakeLayer(
                    new Dec_Frosting(null, Flavor.Chocolate)));

            Console.WriteLine("$" + cake.getCost());
            Console.WriteLine(cake.getDesc());

        }
    }
}