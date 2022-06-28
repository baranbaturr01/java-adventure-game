public class ToolStore extends NormalLocation {
    public ToolStore(Player player) {
        super(player, "ToolStore");
    }

    @Override
    boolean onLocation() {
        System.out.println("You are in the ToolStore");
        System.out.println("What do you want to buy?");
        System.out.println("1- Gun");
        System.out.println("2- Tools");
        System.out.println("3- Exit");

        System.out.println("Please enter your selection: ");
        int select = Location.scanner.nextInt();
        while (select < 1 || select > 3) {
            System.out.println("Wrong !! Please enter your selection: ");
            select = Location.scanner.nextInt();
        }
        switch (select) {
            case 1 -> {
                printWeapon();
                buyWeapon();
            }
            case 2 -> {
                printArmor();
            }
            case 3 -> {
                System.out.println("You exit the ToolStore");
                return false;
            }
        }

        return true;
    }

    public void printArmor() {
        System.out.println("You have bought a armor");
    }

    public void printWeapon() {
        System.out.println("------------GUNS------------");
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + " - " + w.getName() + " <Para : " + w.getPrice() + " Hasar : " + w.getDamage() + ">");
        }
    }

    public void buyWeapon() {

        System.out.println("Please enter your gun selection: ");
        int selectedWeaponId = Location.scanner.nextInt();

        while (selectedWeaponId < 1 || selectedWeaponId > Weapon.weapons().length) {
            System.out.println("Wrong !! Please enter your selection: ");
            selectedWeaponId = Location.scanner.nextInt();
        }
        Weapon selectedWeapon = Weapon.getWeaponObjectById(selectedWeaponId);

        if (selectedWeapon != null) {

            if (this.getPlayer().getMoney() >= selectedWeapon.getPrice()) {

                this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedWeapon.getPrice());
                System.out.println("You have bought " + selectedWeapon.getName());
                System.out.println("Kalan Paranız: " + this.getPlayer().getMoney());
                System.out.println("Önceki Silahınız " + this.getPlayer().getInventory().getWeapon().getName());
                this.getPlayer().getInventory().setWeapon(selectedWeapon);
                System.out.println("Şuan ki Silahınız " + this.getPlayer().getInventory().getWeapon().getName());

            } else {
                System.out.println("You have not enough money!!");
            }
        }
    }
}
