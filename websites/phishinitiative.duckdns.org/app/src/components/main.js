import { MantineProvider } from '@mantine/core';

function Demo() {
  return (
    <MantineProvider withGlobalStyles withNormalizeCSS>
      <App />
    </MantineProvider>
  );
}