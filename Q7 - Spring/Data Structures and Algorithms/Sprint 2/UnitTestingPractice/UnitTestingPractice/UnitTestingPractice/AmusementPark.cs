using AmusementParkProj.Exceptions;
using System;

namespace Locations
{
    public class AmusementPark  
    {
        private const int NUMBER_OF_RIDES = 5;
        public string NameOfPark { get; set; } = "Neumont Park";
        public int CountPeopleInPark { get; set; } = 0;

        public int[] RideWaitTimes { get; set; }

        public enum Food
        {
            HotDog, Hamburger, Water, TurkeyLeg, JustSayingHi
        }


        public AmusementPark()
        {
            RideWaitTimes = new int[NUMBER_OF_RIDES] { 0, 0, 0, 0, 0 };
        }

        public double GetTicketPrice(int age, bool isVeteran)
        {
            if (isVeteran)
            {
                return 20.00;
            }

            if (age >= 0 && age <= 5)
            {
                return 0.00;
            } else if (age > 5 && age <= 12)
            {
                return 50.00;
            } else if(age >= 13 && age <= 19)
            {
                return 75.00;
            } else if(age >= 65)
            {
                return 30.00;
            } else //20-64
            {
                return 100.00;
            }
        }

        public bool PurchaseFood(Food foodItem , double cash)
        {
            switch (foodItem)
            {
                case Food.HotDog:
                    return cash >= 2.50 ? true : false;
                case Food.Hamburger:
                    return cash >= 5.00 ? true : false;
                case Food.Water:
                    return cash >= 0.50 ? true : false;
                case Food.TurkeyLeg:
                    return cash >= 12.00 ? true : false;
                default: //Just Saying Hi
                    throw new JustSayingHiException("Hello"); 
            }
        }

        public void EnterPark()
        {
            CountPeopleInPark++;
        }

        public void LeavePark()
        {
            CountPeopleInPark--;
        }


        public void EnqueueOnRide(int rideId)
        {
            if(rideId >= NUMBER_OF_RIDES || rideId < 0)
            {
                throw new NoSuchRideException();
            }

            RideWaitTimes[rideId] += 1;
        }

        public void DequeueOnRide(int rideId)
        {
            if (rideId >= NUMBER_OF_RIDES || rideId < 0)
            {
                throw new NoSuchRideException();
            }

            RideWaitTimes[rideId] -= 1;
        }
    }
}