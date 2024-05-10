using AmusementParkProj;
using AmusementParkProj.Exceptions;
using Locations;

namespace TestProjecto
{
    [TestClass]
    public class UnitTest1
    {
        AmusementPark amusementPark;

        [TestInitialize]
        public void setup()
        {
            //runs before every test
            amusementPark = new AmusementPark();
        }

        [TestCleanup]
        public void cleanup()
        {
            //runs after each teset
            amusementPark = null;
        }
        [ClassCleanup]
        public static void bigCleanup()
        {
            //runs afer all tests are done
        }

        [TestMethod]
        [DataRow(0)]
        [DataRow(1)]
        [DataRow(2)]
        [DataRow(3)]
        [DataRow(4)]
        [DataRow(5)]
        public void GetTicketPriceForChildShouldBeFree(int age)
        {
            Assert.AreEqual(0.00, amusementPark.GetTicketPrice(age, false));
        }

        [TestMethod]
        [DataRow(0)]
        [DataRow(8)]
        [DataRow(15)]
        [DataRow(69)]
        [DataRow(30)]
        [DataRow(12)]
        public void GetTicketPriceForVeteran(int age)
        {
            Assert.AreEqual(20.00, amusementPark.GetTicketPrice(age, true));
        }

        [TestMethod]
        [DataRow(12)]
        [DataRow(13)]
        [DataRow(140)]
        [DataRow(999)]
        public void CanBuyTurkeyLeg(int money)
        {
            bool canPurchse = amusementPark.PurchaseFood(AmusementPark.Food.TurkeyLeg, money);
            Assert.IsTrue(canPurchse);
        }

        [TestMethod]
        [DataRow(0)]
        [DataRow(1)]
        [DataRow(11)]
        [DataRow(-5)]
        public void CanNotBuyTurkeyLeg(int money)
        {
            bool canPurchse = amusementPark.PurchaseFood(AmusementPark.Food.TurkeyLeg, money);
            Assert.IsFalse(canPurchse);
        }

        [TestMethod]
        public void ParkNameMustContainNeumont()
        {
            StringAssert.Contains(amusementPark.NameOfPark, "Neumont");
        }

        [TestMethod]
        [DataRow(0)]
        [DataRow(5)]
        [DataRow(235)]
        [DataRow(5318008)]
        public void AfterManyPeopleEnterParkCountIncreasesCorrectly(int count)
        {
            for (int i = 0; i < count; i++)
            {
                amusementPark.EnterPark();
            }

            Assert.AreEqual(count, amusementPark.CountPeopleInPark);
        }

        [TestMethod]
        [DataRow(0, 0)]
        [DataRow(5, 3)]
        [DataRow(235, 150)]
        [DataRow(5318008, 12000)]
        public void AfterManyPeopleExitParkCountDecreasesCorrectly(int in_count, int out_count)
        {
            for (int i = 0; i < in_count; i++)
            {
                amusementPark.EnterPark();
            }

            for (int i = 0; i < out_count; i++)
            {
                amusementPark.LeavePark();
            }

            Assert.AreEqual(in_count - out_count, amusementPark.CountPeopleInPark);
        }

        [TestMethod]
        public void WhenPurchasingJustSayHiThrowException()
        {
            Assert.ThrowsException<JustSayingHiException>(() =>
            {
                amusementPark.PurchaseFood(AmusementPark.Food.JustSayingHi, 0);
            });
        }

        // Alternate syntax for the previous
        [TestMethod]
        [ExpectedException(typeof(JustSayingHiException))]
        public void WhenPurchasingJustSayHiThrowException2()
        {
            amusementPark.PurchaseFood(AmusementPark.Food.JustSayingHi, 0);
        }

        [TestMethod]
        [DataRow(0)]
        [DataRow(1)]
        [DataRow(2)]
        [DataRow(3)]
        [DataRow(4)]
        public void ShouldBeAbleToEnqueueOnRide(int rideId)
        {
            amusementPark.EnqueueOnRide(rideId);

            int[] expectedQueue = new int[5];
            expectedQueue[rideId] = 1;

            CollectionAssert.AreEqual(expectedQueue, amusementPark.RideWaitTimes);
        }

        [TestMethod]
        [DataRow (-1)]
        [DataRow (100)]
        [ExpectedException(typeof(NoSuchRideException))]
        public void ShouldNotBeAbleToQueueForRide(int rideId)
        {
            amusementPark.EnqueueOnRide(rideId);
        }

        [TestMethod]
        [DynamicData(nameof(FoodData), DynamicDataSourceType.Method)]
        public void ShouldNotBeAbleToPurchaseFoodDueToLackOfFunds(AmusementPark.Food food, double funds)
        {
            bool ableToPurchase = amusementPark.PurchaseFood(food, funds);
            Assert.IsFalse(ableToPurchase);
        }

        private static IEnumerable<object[]> FoodData()
        {
            return new[]
            {
                new object[] {AmusementPark.Food.HotDog, 2.49 },
                new object[] {AmusementPark.Food.Hamburger, 0 },
                new object[] {AmusementPark.Food.Water, 0.49 },
                new object[] {AmusementPark.Food.TurkeyLeg, 2.49 }
            };
        }
    }
}