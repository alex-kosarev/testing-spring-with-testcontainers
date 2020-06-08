package name.alexkosarev.tutorial.repository;

import name.alexkosarev.tutorial.Framework;

import java.util.List;

public interface FrameworkRepository {

    List<Framework> findAllFrameworks();

    int persistFramework(Framework framework);
}
