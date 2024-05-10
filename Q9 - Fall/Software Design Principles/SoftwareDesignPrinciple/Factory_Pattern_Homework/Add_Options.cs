using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Factory_Pattern_Homework
{
    public partial class Add_Options : Form
    {
        public Add_Options(int itemCount)
        {
            InitializeComponent();


            componentSelect.Text = "box";
            colorSelect.Text = GetDefaultColorFromIndex(itemCount);
            textInput.Text = "Default Text";

            topInput.Text = 30 * ((itemCount % 10) + 1) + "";
            int left = (int)Math.Floor((double)itemCount / 10);
            leftInput.Text = 10 + (100 * left) + "";

            widthInput.Text = 100 + "";
            heightInput.Text = 15 + "";
        }


        public string component => componentSelect?.SelectedItem?.ToString();
        public string backgroundColor => colorSelect?.SelectedItem?.ToString();
        public string text => textInput?.Text;
        public int left => int.Parse(leftInput?.Text);
        public int top => int.Parse(topInput?.Text);
        public int width => int.Parse(widthInput?.Text);
        public int height => int.Parse(heightInput?.Text);

        private void closeButton_Click(object sender, EventArgs e)
        {
            DialogResult = DialogResult.Cancel;
            this.Close();
        }

        private void confirmButton_Click(object sender, EventArgs e)
        {
            DialogResult = DialogResult.OK;
            this.Close();
        }

        private string GetDefaultColorFromIndex(int index)
        {
            switch (index)
            {
                case 0:
                    return "Red";
                case 1:
                    return "Orange";
                case 2:
                    return "Yellow";
                case 3:
                    return "Green";
                case 4:
                    return "Blue";
                case 5:
                    return "Indigo";
                case 6:
                    return "Violet";
                case 7:
                    return "Black";
                case 8:
                    return "Gray";
                case 9:
                    return "White";
                default:
                    return "Transparent";

            }
        }
    }
}
