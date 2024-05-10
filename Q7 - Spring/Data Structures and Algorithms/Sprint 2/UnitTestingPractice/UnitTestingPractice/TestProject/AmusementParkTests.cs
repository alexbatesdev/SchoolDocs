using Locations;

namespace LocationTests
{
    /**
     * Coverage Not Touched Area: #e6b0a5
     * Coverage Partially Touched Area: #ffefce
     * Coverage Touched Area: #e0edfd
     */
    [TestClass]
    public class AmusementParkTests
    {
        AmusementPark park;

        #region Setup and Tear Downs

        [TestInitialize]
        public void Setup()
        {
            // Runs before each test. (Optional)
            park = new AmusementPark();
        }


        [TestCleanup]
        public void TearDown()
        {
            // Runs after each test. (Optional)
            park = null;
        }

        [ClassCleanup]
        public static void TestFixtureTearDown()
        {
            // Runs once after all tests in this class are executed. (Optional)
            // Not guaranteed that it executes instantly after all tests from the class.
        }

        #endregion

        [TestMethod]
        public void ParkIsNamedProperly()
        {
            StringAssert.Contains(park.NameOfPark, "Neumont");
        }

        [TestMethod]
        public void AfterEnteringParkCountIncreases()
        {
            park.EnterPark();
            Assert.AreEqual(park.CountPeopleInPark, 1);
        }

        [TestMethod]
        [DataRow(3)]
        [DataRow(5)]
        [DataRow(100)]
        public void AfterManyPeopleEnterParkCountIncreasesCorrectly(int count)
        {
            for (int i = 0; i < count; i++)
            {
                park.EnterPark();
            }
            Assert.AreEqual(park.CountPeopleInPark, count);
        }

        [TestMethod]
        [DataRow(50, 1)]
        [DataRow(1000, 376)]
        [DataRow(200, 3)]
        public void AfterPeopleLeaveParkCountDecreases(int startCount, int leaveCount)
        {
            park.CountPeopleInPark = startCount;

            for (int i = 0; i < leaveCount; i++)
            {
                park.LeavePark();
            }

            Assert.AreEqual(startCount - leaveCount, park.CountPeopleInPark);
        }

        [TestMethod]
        [DataRow(0)]
        [DataRow(1)]
        [DataRow(2)]
        [DataRow(3)]
        [DataRow(4)]
        [DataRow(5)]
        public void GetTicketPriceForChild(int age)
        {
            Assert.AreEqual(0.00, park.GetTicketPrice(age, false));
        }

        [TestMethod]
        [DataRow(5, true)]
        [DataRow(65, true)]
        [DataRow(10, true)]
        [DataRow(18, true)]
        [DataRow(44, true)]
        public void GetTicketPriceForVeteran(int age, bool isVeteran)
        {
            AmusementPark park = new AmusementPark();
            Assert.AreEqual(20.00, park.GetTicketPrice(age, isVeteran));
        }

        [TestMethod]
        public void CantPurchaseHamburgerWithTooLittleMoney()
        {
            AmusementPark park = new AmusementPark();

            bool ableToPurchase = park.PurchaseFood(AmusementPark.Food.Hamburger, 4.99);

            Assert.IsFalse(ableToPurchase, "Should not be able to purchase hamburger. Not enough money");
        }

        [TestMethod]
        [ExpectedException(typeof(JustSayingHiException))]
        public void WhenPurchasingFoodJustSayingHiThrowsException()
        {
            AmusementPark park = new AmusementPark();
            park.PurchaseFood(AmusementPark.Food.JustSayingHi, 9999);
        }

        //Skipping a test
        [TestMethod]
        [Ignore]
        public void CantPurchaseWaterWithCorrectMoney()
        {
            AmusementPark park = new AmusementPark();

            bool ableToPurchase = park.PurchaseFood(AmusementPark.Food.Water, 4.99);

            Assert.IsTrue(ableToPurchase, "Should be able to purchase water");
        }

        [TestMethod]
        [DataRow(0)]
        [DataRow(1)]
        [DataRow(2)]
        [DataRow(3)]
        [DataRow(4)]
        public void EnqueuingOnRideShouldIncreaseWaitTime(int rideId)
        {
            park.EnqueueOnRide(rideId);

            int[] expected = new int[5];
            expected[rideId] = 1;

            CollectionAssert.AreEqual(expected, park.RideWaitTimes);
        }

        [TestMethod]
        [DataRow(0)]
        [DataRow(1)]
        [DataRow(2)]
        [DataRow(3)]
        [DataRow(4)]
        public void DequeuingOnRideShouldIncreaseWaitTime(int rideId)
        {
            int[] startingQueues = new int[5] { 20, 3, 15, 6, 4 };
            Array.Copy(startingQueues, park.RideWaitTimes, startingQueues.Length);
            
            int[] expected = new int[5];
            Array.Copy(startingQueues, expected, startingQueues.Length);
            
            expected[rideId] -= 1;

            park.DequeueOnRide(rideId);

            CollectionAssert.AreEqual(expected, park.RideWaitTimes);
        }

        [TestMethod]
        [DynamicData(nameof(FoodData), DynamicDataSourceType.Method)]
        public void ShouldBeAbleToPurhaseFood(AmusementPark.Food food, double cash)
        {
            AmusementPark park = new AmusementPark();
            bool ableToPurchase = 
                park.PurchaseFood(food, cash);

            Assert.IsTrue(ableToPurchase);
        }

        private static IEnumerable<object[]> FoodData()
        {
            return new[]
            {
            new object[] { AmusementPark.Food.HotDog, 2.50 },
            new object[] { AmusementPark.Food.Hamburger, 5.00 },
            new object[] { AmusementPark.Food.Water, 0.50 },
            new object[] { AmusementPark.Food.TurkeyLeg, 12.00 }
        };
        }



    }
}