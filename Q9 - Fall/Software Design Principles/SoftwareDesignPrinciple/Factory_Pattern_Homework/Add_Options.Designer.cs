namespace Factory_Pattern_Homework
{
    partial class Add_Options
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.confirmButton = new System.Windows.Forms.Button();
            this.label2 = new System.Windows.Forms.Label();
            this.leftInput = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.topInput = new System.Windows.Forms.TextBox();
            this.colorSelect = new System.Windows.Forms.ComboBox();
            this.label4 = new System.Windows.Forms.Label();
            this.heightInput = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.widthInput = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.textInput = new System.Windows.Forms.TextBox();
            this.closeButton = new System.Windows.Forms.Button();
            this.componentSelect = new System.Windows.Forms.ComboBox();
            this.label1 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // confirmButton
            // 
            this.confirmButton.Location = new System.Drawing.Point(304, 116);
            this.confirmButton.Name = "confirmButton";
            this.confirmButton.Size = new System.Drawing.Size(75, 23);
            this.confirmButton.TabIndex = 0;
            this.confirmButton.Text = "Confirm";
            this.confirmButton.UseVisualStyleBackColor = true;
            this.confirmButton.Click += new System.EventHandler(this.confirmButton_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 53);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(28, 16);
            this.label2.TabIndex = 2;
            this.label2.Text = "Left";
            // 
            // leftInput
            // 
            this.leftInput.Location = new System.Drawing.Point(12, 72);
            this.leftInput.Name = "leftInput";
            this.leftInput.Size = new System.Drawing.Size(100, 22);
            this.leftInput.TabIndex = 4;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(115, 53);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(32, 16);
            this.label3.TabIndex = 5;
            this.label3.Text = "Top";
            // 
            // topInput
            // 
            this.topInput.Location = new System.Drawing.Point(118, 72);
            this.topInput.Name = "topInput";
            this.topInput.Size = new System.Drawing.Size(100, 22);
            this.topInput.TabIndex = 6;
            // 
            // colorSelect
            // 
            this.colorSelect.FormattingEnabled = true;
            this.colorSelect.Items.AddRange(new object[] {
            "Red",
            "Orange",
            "Yellow",
            "Green",
            "Blue",
            "Indigo",
            "Violet",
            "White",
            "Gray",
            "Black",
            "Transparent"});
            this.colorSelect.Location = new System.Drawing.Point(144, 28);
            this.colorSelect.Name = "colorSelect";
            this.colorSelect.Size = new System.Drawing.Size(121, 24);
            this.colorSelect.TabIndex = 7;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(141, 9);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(115, 16);
            this.label4.TabIndex = 8;
            this.label4.Text = "Background Color";
            // 
            // heightInput
            // 
            this.heightInput.Location = new System.Drawing.Point(117, 116);
            this.heightInput.Name = "heightInput";
            this.heightInput.Size = new System.Drawing.Size(100, 22);
            this.heightInput.TabIndex = 12;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(114, 97);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(46, 16);
            this.label5.TabIndex = 11;
            this.label5.Text = "Height";
            // 
            // widthInput
            // 
            this.widthInput.Location = new System.Drawing.Point(12, 116);
            this.widthInput.Name = "widthInput";
            this.widthInput.Size = new System.Drawing.Size(100, 22);
            this.widthInput.TabIndex = 10;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(12, 97);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(41, 16);
            this.label6.TabIndex = 9;
            this.label6.Text = "Width";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(223, 55);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(33, 16);
            this.label7.TabIndex = 13;
            this.label7.Text = "Text";
            // 
            // textInput
            // 
            this.textInput.Location = new System.Drawing.Point(224, 72);
            this.textInput.Name = "textInput";
            this.textInput.Size = new System.Drawing.Size(100, 22);
            this.textInput.TabIndex = 14;
            // 
            // closeButton
            // 
            this.closeButton.Location = new System.Drawing.Point(223, 116);
            this.closeButton.Name = "closeButton";
            this.closeButton.Size = new System.Drawing.Size(75, 23);
            this.closeButton.TabIndex = 15;
            this.closeButton.Text = "Cancel";
            this.closeButton.UseVisualStyleBackColor = true;
            this.closeButton.Click += new System.EventHandler(this.closeButton_Click);
            // 
            // componentSelect
            // 
            this.componentSelect.FormattingEnabled = true;
            this.componentSelect.Items.AddRange(new object[] {
            "box",
            "checkbox",
            "button"});
            this.componentSelect.Location = new System.Drawing.Point(12, 28);
            this.componentSelect.Name = "componentSelect";
            this.componentSelect.Size = new System.Drawing.Size(121, 24);
            this.componentSelect.TabIndex = 16;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(76, 16);
            this.label1.TabIndex = 17;
            this.label1.Text = "Component";
            // 
            // Add_Options
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(388, 146);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.componentSelect);
            this.Controls.Add(this.closeButton);
            this.Controls.Add(this.textInput);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.heightInput);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.widthInput);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.colorSelect);
            this.Controls.Add(this.topInput);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.leftInput);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.confirmButton);
            this.Name = "Add_Options";
            this.Text = "Add_Options";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button confirmButton;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox leftInput;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox topInput;
        private System.Windows.Forms.ComboBox colorSelect;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox heightInput;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox widthInput;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox textInput;
        private System.Windows.Forms.Button closeButton;
        private System.Windows.Forms.ComboBox componentSelect;
        private System.Windows.Forms.Label label1;
    }
}