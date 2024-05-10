using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CorpRest_DataIntegration.Restaurant_A
{
    public class MenuItem_A : MenuSubItem_A
    {
        private List<MenuSubItem_A> _subItems = null;
        private MenuType _menuType;

        public MenuItem_A(string title, double cost, MenuType menuType) : base(title, cost)
        {
            _menuType = menuType;
            _subItems = new List<MenuSubItem_A>();
        }

        public List<MenuSubItem_A> SubItems { get { return _subItems; } }
        public MenuType ItemMenuType { get { return _menuType; } }

        public enum MenuType { Common, Breakfast, Lunch, Dinner }

        public void AddSubItem(MenuSubItem_A subItem)
        {
            if (subItem == null)
            {
                return;
            }
            _subItems.Add(subItem);
        }
    }
}
