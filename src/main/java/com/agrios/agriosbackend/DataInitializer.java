package com.agrios.agriosbackend;

import com.agrios.agriosbackend.entity.*;
import com.agrios.agriosbackend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final FarmRepository farmRepository;
    private final CropRepository cropRepository;
    private final CropProblemRepository cropProblemRepository;
    private final AdvisoryRepository advisoryRepository;
    private final ExpenseRepository expenseRepository;
    private final InventoryRepository inventoryRepository;
    private final IrrigationRepository irrigationRepository;
    private final TaskRepository taskRepository;
    private final WeatherRepository weatherRepository;
    private final HarvestRepository harvestRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           FarmRepository farmRepository,
                           CropRepository cropRepository,
                           CropProblemRepository cropProblemRepository,
                           AdvisoryRepository advisoryRepository,
                           ExpenseRepository expenseRepository,
                           InventoryRepository inventoryRepository,
                           IrrigationRepository irrigationRepository,
                           TaskRepository taskRepository,
                           WeatherRepository weatherRepository,
                           HarvestRepository harvestRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.farmRepository = farmRepository;
        this.cropRepository = cropRepository;
        this.cropProblemRepository = cropProblemRepository;
        this.advisoryRepository = advisoryRepository;
        this.expenseRepository = expenseRepository;
        this.inventoryRepository = inventoryRepository;
        this.irrigationRepository = irrigationRepository;
        this.taskRepository = taskRepository;
        this.weatherRepository = weatherRepository;
        this.harvestRepository = harvestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Ensure Admin user
        User admin = userRepository.findByEmail("admin@agrios.com").orElse(null);
        if (admin == null) {
            admin = new User("AgriOS Admin", "admin@agrios.com", passwordEncoder.encode("admin123"), Role.ADMIN);
            userRepository.save(admin);
        } else {
            admin.setRole(Role.ADMIN);
            if (!passwordEncoder.matches("admin123", admin.getPassword())) {
                admin.setPassword(passwordEncoder.encode("admin123"));
            }
            userRepository.save(admin);
        }

        // Ensure Officer user
        User officer = userRepository.findByEmail("officer@agrios.com").orElse(null);
        if (officer == null) {
            officer = new User("AgriOS Officer", "officer@agrios.com", passwordEncoder.encode("officer123"), Role.OFFICER);
            userRepository.save(officer);
        } else {
            officer.setRole(Role.OFFICER);
            if (!passwordEncoder.matches("officer123", officer.getPassword())) {
                officer.setPassword(passwordEncoder.encode("officer123"));
            }
            userRepository.save(officer);
        }

        // Ensure Farmer user
        User farmer = userRepository.findByEmail("farmer@gmail.com").orElse(null);
        if (farmer == null) {
            farmer = new User("Test Farmer", "farmer@gmail.com", passwordEncoder.encode("farmer123"), Role.FARMER);
            farmer = userRepository.save(farmer);
        } else {
            farmer.setRole(Role.FARMER);
            if (!passwordEncoder.matches("farmer123", farmer.getPassword())) {
                farmer.setPassword(passwordEncoder.encode("farmer123"));
            }
            farmer = userRepository.save(farmer);
        }

        // Ensure Farms exist and have valid names & fields
        List<Farm> allFarms = farmRepository.findAll();
        if (allFarms.isEmpty()) {
            Farm f1 = new Farm("Green Valley Organic Farm", "Ibrahimpatnam, Hyderabad", 25.5, "Loamy Soil", "Drip Irrigation", farmer);
            farmRepository.save(f1);
            Farm f2 = new Farm("Sunrise Agro Acres", "Shamshabad, Rangareddy", 18.0, "Black Cotton Soil", "Sprinkler System", farmer);
            farmRepository.save(f2);
            allFarms = farmRepository.findAll();
        } else {
            String[] defaultNames = {
                "Green Valley Organic Farm",
                "Sunrise Agro Acres",
                "Delta Precision Fields",
                "Highland Agro Station"
            };
            int idx = 0;
            for (Farm f : allFarms) {
                if (f.getFarmName() == null || f.getFarmName().trim().isEmpty()) {
                    f.setFarmName(defaultNames[idx % defaultNames.length]);
                }
                if (f.getSoilType() == null || f.getSoilType().trim().isEmpty()) {
                    f.setSoilType("Loamy Soil");
                }
                if (f.getIrrigationType() == null || f.getIrrigationType().trim().isEmpty()) {
                    f.setIrrigationType("Drip Irrigation");
                }
                if (idx < 2) {
                    f.setOwner(farmer);
                }
                farmRepository.save(f);
                idx++;
            }
        }
        Farm farm = allFarms.get(0);

        // Ensure Crops have valid names & fields
        List<Crop> allCrops = cropRepository.findAll();
        if (allCrops.isEmpty()) {
            Crop c = new Crop("Golden Wheat", "HD-2967", "Rabi 2026", "2026-01-15", "2026-04-20", "GROWING", farm);
            cropRepository.save(c);
            allCrops = cropRepository.findAll();
        } else {
            for (Crop c : allCrops) {
                if (c.getCropName() == null || c.getCropName().trim().isEmpty()) {
                    c.setCropName("Golden Wheat");
                }
                if (c.getVariety() == null || c.getVariety().trim().isEmpty()) {
                    c.setVariety("HD-2967");
                }
                if (c.getSeason() == null || c.getSeason().trim().isEmpty()) {
                    c.setSeason("Rabi 2026");
                }
                if (c.getStatus() == null || c.getStatus().trim().isEmpty()) {
                    c.setStatus("GROWING");
                }
                cropRepository.save(c);
            }
        }
        Crop crop = allCrops.get(0);

        // Ensure sample CropProblem & Advisory if none exist
        if (cropProblemRepository.count() == 0) {
            CropProblem problem = new CropProblem("Yellow Leaf Rust", "Yellow pustules appearing on upper leaf blades", "2026-03-01", "INVESTIGATING", crop);
            problem = cropProblemRepository.save(problem);

            if (advisoryRepository.count() == 0) {
                Advisory advisory = new Advisory("Apply Propiconazole 25% EC @ 1ml/L of water during evening hours.", "2026-03-02", "Dr. Rajesh Officer", problem);
                advisoryRepository.save(advisory);
            }
        }

        // Ensure sample Inventory
        if (inventoryRepository.count() == 0) {
            inventoryRepository.save(new Inventory("NPK Fertilizer 19-19-19", "Fertilizer", 50, "Bags (50kg)", 75000.0, farm));
            inventoryRepository.save(new Inventory("Certified Wheat Seeds", "Seeds", 20, "Bags (20kg)", 24000.0, farm));
        }

        // Ensure sample Tasks
        if (taskRepository.count() == 0) {
            taskRepository.save(new Task("Soil Moisture Inspection", "Check sensor readings in Block A & B", "2026-03-15", "HIGH", "PENDING", farm));
            taskRepository.save(new Task("Organic Compost Application", "Apply compost across Section C", "2026-03-20", "MEDIUM", "IN_PROGRESS", farm));
        }

        // Ensure sample Irrigation
        if (irrigationRepository.count() == 0) {
            irrigationRepository.save(new IrrigationRecord("2026-03-10", 12000.0, "Drip Irrigation", "Scheduled morning drip cycle completed", farm));
        }

        // Ensure sample Expenses
        if (expenseRepository.count() == 0) {
            expenseRepository.save(new Expense("Fertilizers & Nutrients", 25000.0, "2026-02-20", "Seasonal nutrient purchase for wheat", farm));
            expenseRepository.save(new Expense("Labor & Weeding", 12000.0, "2026-03-05", "Field preparation and weeding operations", farm));
        }

        // Ensure sample Weather
        if (weatherRepository.count() == 0) {
            weatherRepository.save(new WeatherRecord("2026-03-14", 28.5, 62.0, 0.0, "Sunny", farm));
            weatherRepository.save(new WeatherRecord("2026-03-15", 27.0, 68.0, 2.5, "Scattered Showers", farm));
        }

        // Ensure sample Harvest
        if (harvestRepository.count() == 0) {
            harvestRepository.save(new Harvest("2026-02-15", 3500.0, "kg", 87500.0, "Grade A Premium", crop));
        }
    }
}
